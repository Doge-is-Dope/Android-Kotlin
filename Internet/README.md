# Internet

- RESTful service: Representational State Transfer Architecture uses 
operations including ```GET```, ```POST```, ```PUT``` and ```DELETE```.
- URI: Comparing to URL, URI specifies the data you want; **A URL is a type of URI**.



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

Compare to [Retrofit Usage Step 2]()
```kotlin
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
```

5. Update the Interface to return a list of designated objects

Compare to [Retrofit Usage Step 3]()
```kotlin
interface MarsApiService {
    @GET("realestate")
    fun getProperties(): Call<List<MarsProperty>>
}
```