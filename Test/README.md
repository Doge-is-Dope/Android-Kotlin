#### Rounded Corner ImageView

1. Create a drawable 
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners android:radius="8dp" />
</shape>
```

2. Apply the drawable to the ImageView
```xml
<ImageView android:background="@drawable/bg_rounded_card"/>
```

3. Set the ```clipToOutline``` to true
```kotlin
binding.imgPost.clipToOutline = true
``` 

Somehow the following isn't working

```xml
<ImageView  android:clipToOutline="true"/>
```