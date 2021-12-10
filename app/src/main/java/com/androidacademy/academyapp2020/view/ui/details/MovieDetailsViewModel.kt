package com.androidacademy.academyapp2020.view.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.entity.Movie
import com.androidacademy.academyapp2020.data.repository.MovieRepository
import com.androidacademy.academyapp2020.utils.LoadStatus
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.Loading
                _movie.value = movieRepository.loadMovieDetails(movieId)
                _status.value = LoadStatus.Success
            } catch (exception: Exception) {
                _status.value = LoadStatus.Error
            }
        }
    }
}