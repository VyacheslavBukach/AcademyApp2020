package com.androidacademy.academyapp2020.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.repository.IRepository
import com.androidacademy.academyapp2020.utils.LoadStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: IRepository) : ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun getMovies(context: Context) {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.Loading
//                delay(1000)
                _moviesList.value = repository.loadMoviesFromRepository(context)
                _status.value = LoadStatus.Success
            } catch (exception: Exception) {
                _status.value = LoadStatus.Error
            }
        }
    }
}