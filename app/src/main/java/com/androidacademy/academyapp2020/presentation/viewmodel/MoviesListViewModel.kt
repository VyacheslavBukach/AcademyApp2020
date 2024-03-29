package com.androidacademy.academyapp2020.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidacademy.academyapp2020.domain.entity.Movie
import com.androidacademy.academyapp2020.data.repository.MovieDataSource
import com.androidacademy.academyapp2020.domain.repository.MovieRepository
import com.androidacademy.academyapp2020.utils.LoadStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val moviesList: LiveData<PagedList<Movie>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        moviesList = initializedPagedListBuilder(config).build()
    }

    fun getMovies(): LiveData<PagedList<Movie>> = moviesList

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Movie> {
        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return MovieDataSource(movieRepository)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    //    fun getMovies() {
//        viewModelScope.launch {
//            try {
//                _status.value = LoadStatus.Loading
//                _moviesList.value = movieRepository.loadMoviesList()
//                _status.value = LoadStatus.Success
//            } catch (exception: Exception) {
//                _status.value = LoadStatus.Error
//            }
//        }
//    }
}