package com.chunchiehliang.kotlin.internet.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunchiehliang.kotlin.internet.network.MarsApi
import com.chunchiehliang.kotlin.internet.network.MarsApiFilter
import com.chunchiehliang.kotlin.internet.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    enum class MarsApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status


    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty


    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        coroutineScope.launch {

            try {
                _status.value = MarsApiStatus.LOADING

                val listResult = MarsApi.retrofitService.getProperties(filter.value)

                _status.value = MarsApiStatus.DONE

                _properties.value = listResult

            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
            }
        }
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
