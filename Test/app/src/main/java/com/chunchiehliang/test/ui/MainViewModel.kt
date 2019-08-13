package com.chunchiehliang.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainViewModel : ViewModel(){
    private val _initiated = MutableLiveData<Boolean>()
    val initiated: LiveData<Boolean>
        get() = _initiated

    fun onInitiated() {
        _initiated.value = true
    }

    fun onInitiationCompleted() {
        _initiated.value = null
    }
}