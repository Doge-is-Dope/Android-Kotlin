package com.chunchiehliang.kotlin.nestedrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunchiehliang.kotlin.nestedrecyclerview.Repository.createDummyMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    enum class MovieApiStatus { LOADING, ERROR, DONE }

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>>
        get() = _genreList

    private val _navigateToMovieDetail = MutableLiveData<Movie>()
    val navigateToMovieDetail: LiveData<Movie>
        get() = _navigateToMovieDetail

    fun onMovieClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }

    init {
        getNowPlayingMovies()
    }


    private fun getNowPlayingMovies() {
        coroutineScope.launch {
            try {
                _status.value = MovieApiStatus.LOADING

                _movieList.value = createDummyMovies()

                _status.value = MovieApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
            }
        }
    }


    /**
     * Called when the ViewModel is dismantled. At this point, we want to cancel all coroutines;
     * otherwise we end up with processes that have nowhere to return to
     * using memory and resources.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}