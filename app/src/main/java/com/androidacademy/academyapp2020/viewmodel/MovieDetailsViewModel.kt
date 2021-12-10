package com.androidacademy.academyapp2020.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.repository.IRepository
import com.androidacademy.academyapp2020.utils.LoadStatus
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: IRepository) : ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getMovie(context: Context, movieId: Int?) {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.Loading
                _movie.value = repository.loadMovieFromRepository(context, movieId)
                _status.value = LoadStatus.Success
            } catch (exception: Exception) {
                _status.value = LoadStatus.Error
            }
        }
    }
}