package com.androidacademy.academyapp2020.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidacademy.academyapp2020.data.repository.IRepository

class ViewModelFactory(private val repository: IRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            MoviesListViewModel::class.java -> MoviesListViewModel(repository)
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repository)
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
}