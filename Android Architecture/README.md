# Offline Cache

![network caching](images/network_caching.png)

#### Network Caching
- Cache results per query
- Http Caching
- Store internet results on disk

#### Cache Validation
Knowing the cache is out of date 

#### Best way to store offline cache
Using the SQL database is handy for huge and structured data. E.g. ```Room```


|               | Room            | SharedPreference | File System |
|:--------------|:----------------|:-----------------|:------------|
| Data Format   | Structured Data | Key / Value      | Anything    |
| Data Size     | Big             | Small            | Big         |
| Complex Query | Yes             | No               | Difficult   |

#### Offline Cache Pattern
1. Show data from the database
2. Make sure the server saves **before** updating 
the database




### 1. Separate Internet Results from the Database Models

Using the same POJOs for both results from the Internet & entities in the database is a bad practice.
It's better to have three types of data objects:

1. Data Transfer Objects: Data from/to the Internet 
2. Domain Objects: Core data used in the app
3. Database Object: Entities stored in the database


### 1.1 Create Domain Objects
```kotlin
// domain/Models.kt
data class Video(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
)
```
### 1.2 Create Data Transfer Objects
```kotlin
// network/DataTransferObjects.kt
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)
```

Add an extension function which converts data transfer objects to domain objects

```kotlin
// network/DataTransferObjects.kt
fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}
```

### 1.3 Create Database Object
```kotlin
// database/DatabaseEntities.kt
@Entity
data class DatabaseVideo (
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)
```

Add an extension function which converts database objects to domain objects

```kotlin
// database/DatabaseEntities.kt
fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video (
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}
```

In data transfer objects, add an extension function which converts data transfer objects to database objects

```kotlin
// network/DataTransferObjects.kt
fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map {
        DatabaseVideo (
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }.toTypedArray()
}
```

### 2. Add DAO (Database Access Object)

- Make the query returns ```LiveData``` so that when the query is called from the UI thread, Room will do the database query in the background which will not block the UI thread.

- ```vararg``` in Kotlin allows passing variable number of arguments.

```kotlin
// database/Room.kt
@Dao
interface VideoDao {
    @Query("select * from databasevideo")
    fun getVideos(): LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseVideo)
}
``` 

### 3. Create Database class

Inside the database, create a variable of DAO

```kotlin
// database/Room.kt
@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VideosDatabase : RoomDatabase() {
    abstract val videoDao: VideoDao
}
```

Create a singleton instance of the database

```kotlin
private lateinit var INSTANCE: VideosDatabase

fun getDatabase(context: Context): VideosDatabase {
    synchronized(VideosDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                VideosDatabase::class.java,
                "videos").build()
        }
    }
    return INSTANCE
}
```

### 4. Create Repository class

A repository is a API to data sources

```kotlin
class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    suspend fun refreshVideo() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}
```

### 5. Use the Repository in the ViewModel

#### 5.1 Create the database singleton
```kotlin
private val database = getDatabase(application)
```

#### 5.2 Create the repository
```kotlin
private val videosRepository = VideosRepository(database)
```

#### 5.3 Fetch the Internet data and update the database in ```init```
```kotlin
init {
    viewModelScope.launch {
        videosRepository.refreshVideos()
    }
}
``` 

#### 5.4 Get the database data from the repository
```kotlin
val playlist = videosRepository.videos
```


### Pre-fetch the data using ```WorkManager```

1. Create a Worker class and do the work in the background

2. Schedule the worker in the Application Class (Set the constraints if they're needed)
