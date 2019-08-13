package com.chunchiehliang.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.test.domain.Post
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class PostListViewModel : ViewModel() {

    private var currentJob: Job? = null

    private val _postList = MutableLiveData<List<Post>>()
    val postList: LiveData<List<Post>>
        get() = _postList


    init {
        onQueryChanged()
    }

    private fun onQueryChanged() {
        currentJob?.cancel() // if a previous query is running cancel it before starting another
        currentJob = viewModelScope.launch {
            try {
                _postList.value = listOf(
                    Post(1, "aaa"),
                    Post(2, "bbb"),
                    Post(3, "ccc"),
                    Post(4, "ddd"),
                    Post(5, "eee"),
                    Post(6, "fff")
                )

            } catch (e: IOException) {
                _postList.value = listOf()
            }
        }
    }
}

