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

### 2. Add DAO

```kotlin
// database/Room.kt
@Dao
interface VideoDao {
    @Query("select * from databasevideo")
    fun getVideos(): List<DatabaseVideo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseVideo)
}
``` 