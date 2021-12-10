package com.androidacademy.academyapp2020.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.androidacademy.academyapp2020.data.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDataSource(
    private val movieRepository: MovieRepository,
) : PageKeyedDataSource<Int, Movie>() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        scope.launch {
            try {
                callback.onResult(movieRepository.loadMoviesList(1), null, 2)
            } catch (e: Exception) {
                Log.i("MovieDataSource", e.stackTraceToString())
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                val key = params.key
                val previousKey = if (key == 1) null else key.dec()
                callback.onResult(movieRepository.loadMoviesList(key), previousKey)
            } catch (e: Exception) {
                Log.i("MovieDataSource", e.stackTraceToString())
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                callback.onResult(movieRepository.loadMoviesList(params.key), params.key.inc())
            } catch (e: Exception) {
                Log.i("MovieDataSource", e.stackTraceToString())
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}