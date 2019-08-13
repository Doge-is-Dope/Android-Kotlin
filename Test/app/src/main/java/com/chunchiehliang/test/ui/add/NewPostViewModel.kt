package com.chunchiehliang.test.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class NewPostViewModel : ViewModel() {
    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    fun onCloseClicked() {
        Timber.d("Clicked!")
        _navigateToHome.value = true
    }

    fun onHomeActivityNavigated() {
        _navigateToHome.value = null
    }
}
