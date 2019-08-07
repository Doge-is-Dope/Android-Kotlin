# Android App Design

##### Technical Concerns
- Rotation handling: Does the data survive when the device is rotated?
- Offline cache: Is the data still available during offline?
- Data validation: Is the data correct?

##### Accessibility
- Vision impairment: Does the app work for color blinded? Does the view size perfectly for the resolution?
- Input device: Is there any case that a user can't touch the screen?
- Talk back: ```accessibilityLiveRegion```, ```contentDescription```, ```hint```
- Right-To-Left Support: ```supportsRtl``` in Manifest, ```autoMirrored``` for a image
- Test the app using [Accessibility Scanner](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor&hl=en_US) 
- [Reference](https://developer.android.com/guide/topics/ui/accessibility/testing)
 
#### Material Design Chip
1. Add a ```ChipGroup``` in the layout

```xml
<HorizontalScrollView
    android:id="@+id/chips"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintHorizontal_bias="0"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent">

    <com.google.android.material.chip.ChipGroup
        android:id="@+id/region_list"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="@dimen/spacing_normal"
        app:singleSelection="true" />
</HorizontalScrollView>
```

2. Create a ```Chip``` layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- chip_region.xml -->
<com.google.android.material.chip.Chip 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    style="@style/Widget.MaterialComponents.Chip.Choice"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:chipBackgroundColor="@color/selected_highlight"
    app:checkedIconVisible="true"
    tools:checked="true"
    tools:text="@string/app_name">
</com.google.android.material.chip.Chip>
```

3. Create an observer on the list
```kotlin
viewModel.regionList.observe(viewLifecycleOwner, object: Observer<List<String>> {
    override fun onChanged(data: List<String>?) {
        data ?: return
    }
})
```

4. Use ```map()``` to iterate over the list of chip's name and 
create a Chip for each item, 
then return the results as a new list called children
```kotlin
val chipGroup = binding.regionList

val children = data.map { regionName ->
    val chip = LayoutInflater
        .from(chipGroup.context)
        .inflate(R.layout.chip_region, chipGroup, false) as Chip

    chip.text = regionName
    chip.tag = regionName
    chip.setOnCheckedChangeListener { button, isChecked ->
        viewModel.onFilterChanged(button.tag as String, isChecked)
    }
    chip
}
```

5. Remove any views that are already in the ```chipGroup```
```kotlin
chipGroup.removeAllViews()
```

6. Add ```chip```s to the ```chipGroup```
```kotlin
for (chip in children) {
    chipGroup.addView(chip)
}
```



  

