# Internet

**Retrofit version ```2.6.0```**

- RESTful service: Representational State Transfer Architecture uses 
operations including ```GET```, ```POST```, ```PUT``` and ```DELETE```.
- URI: Comparing to URL, URI specifies the data you want; **A URL is a type of URI**.

### Outline
1. [Retrofit: Http API Interface](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#retrofit-usage)
2. [Moshi: JSON Parser](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#parse-json-using-moshi)
3. [Kotlin Coroutine: Asynchronous Service](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#user-retrofit-with-coroutines)
4. [Glide: Image Loading Library](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#display-an-internet-image-using-glide)
5. [Parcelize: Turns a Kotlin data object with simple and Parcelable types into a Parcelable object]()


### Retrofit Basics: What Retrofit needs to build a Network API

1. Base URL of our web service

2. Converter factory that allows Retrofit to return the server response 
in a useful format

### Retrofit Usage

1. Add Retrofit dependencies

```
implementation "com.squareup.retrofit2:retrofit: $version_retrofit"
implementation "com.squareup.retrofit2:converter-scalars:$version_retrofit"
```

2. Use Retrofit Builder with ```ScalarsConverterFactory``` and Base URL

```ScalarsConverterFactory``` lets Retrofit transform the JSON response into a String
```kotlin
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
```

3. Implement the MarsApiService interface that talks to the server like ```@GET```.
```kotlin
interface MarsApiService {
    @GET("realestate")
    fun getProperties() : Call<String>
}
```

4. Create the object using Retrofit to implement the MarsApiService
```kotlin
object MarsApi {
    val retrofitService : MarsApiService by lazy { 
        retrofit.create(MarsApiService::class.java) 
    }
}
```

5. Enqueue the Retrofit request in the ViewModel
```kotlin
class OverviewViewModel : ViewModel() {
    private fun getMarsRealEstateProperties() {
        // enqueue the Retrofit request and implement the callbacks
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
           // ...
        })
        _response.value = "Set the Mars API Response here!"
    }
}
```

Override the required Retrofit callbacks to assign the JSON response or
 an error message to the LiveData value
 
```kotlin
private fun getMarsRealEstateProperties() {
    // enqueue the Retrofit request and implement the callbacks
    MarsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            _response.value = "Failure: ${t.message}"
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            _response.value = response.body()
        }

    })
}
```

### Parse JSON using Moshi

1. Add dependencies for Moshi (Moshi-Kotlin, and the Retrofit Moshi converter)
```
implementation "com.squareup.retrofit2:converter-moshi:$version_retrofit"
implementation "com.squareup.moshi:moshi:$version_moshi"
implementation "com.squareup.moshi:moshi-kotlin:$version_moshi"
```

2. Create a Kotlin data class with properties that match the JSON response fields
```kotlin
data class MarsProperty (
    val id: String,
    val img_src: String,
    val type: String,
    val price: Double
)
```
Rename the variables by using ```@Json``` annotation to map the JSON field
```kotlin
data class MarsProperty(
    val id: String,
    @Json(name = "img_src")
    val imgSrcUrl: String,
    val type: String,
    val price: Double
)
```

3. Use the Moshi Builder to create a Moshi object with the ```KotlinJsonAdapterFactory```
```kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
```

4. In the ```retrofit``` object, change the ConverterFactory to a ```MoshiConverterFactory``` with the ```moshi``` Object

Compare to [Retrofit Usage Step 2](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#retrofit-usage)
```kotlin
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
```

5. Update the Interface to return a list of designated objects

Compare to [Retrofit Usage Step 3](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#retrofit-usage)
```kotlin
interface MarsApiService {
    @GET("realestate")
    fun getProperties(): Call<List<MarsProperty>>
}
```

6. Fix the Callback's argument in the ViewModel in [Retrofit Usage Step 5](https://github.com/chunchiehliang/AndroidKotlin/tree/master/Internet#retrofit-usage)
```kotlin
private fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsProperty>> {
            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                _response.value = "Failure: ${t.message}"
        }

        override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
            _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
        }
    })
}
```

### User Retrofit with Coroutines
1. Add dependencies for Coroutines
```
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version_kotlin_coroutines"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version_kotlin_coroutines"
```

2. Add ```suspend``` modifier to the function in the interface

Compare to [Parse JSON using Moshi Step 5]()
```kotlin
interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): Call<List<MarsProperty>>
}
```

Then, change the ```Call<List<Data>>``` to ```List<MarsProperty>```
```kotlin
interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}
```

3. In ViewModel, create variables for a coroutine Job and a CoroutineScope using the Main Dispatcher
```kotlin
private var viewModelJob = Job()
private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
```

4. In ViewModel, replace ```enqueue```  with coroutine for making API request

Compare to [Parse JSON using Moshi Step 6]()
```kotlin
private fun getMarsRealEstateProperties() {
    coroutineScope.launch {
        val listResult = MarsApi.retrofitService.getProperties()
            try {
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
        }
    }
}
```

5. Override ```onCleared()``` and cancel the Job when the ViewModel is finished
```kotlin
override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
}
```

### Display an internet image using Glide

1. Add dependencies for Glide
```
implementation "com.github.bumptech.glide:glide:$version_glide"
```

2. Create a ```BindingAdapter``` to convert imgUrl to a URI with the https scheme

```kotlin
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}
```

3. In layout, add a viewModel data variable and bind the ImageView using BindingAdapter
```xml
<data>
    <variable
        name="viewModel"
        type="com.chunchiehliang.kotlin.internet.overview.OverviewViewModel" />
</data>
```

```xml
<ImageView
    app:imageUrl="@{viewModel.property.imgSrcUrl}" />
```

4. Optional: Add a loading placeholder & an error image
```kotlin
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
```


### Parcelize

1. Add ```androidExtensions``` in ```build.gradle (module)```
```
android {
    androidExtensions {
        experimental = true
    }
}
```
2. Set the annotation and the return type
```kotlin
@Parcelize
data class MarsProperty(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double) : Parcelable {

}
```

### Reference

- [Glide](https://bumptech.github.io/glide/)