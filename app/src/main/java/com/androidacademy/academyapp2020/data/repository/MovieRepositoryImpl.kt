package com.androidacademy.academyapp2020.data.repository

import android.util.Log
import com.androidacademy.academyapp2020.domain.entity.Movie
import com.androidacademy.academyapp2020.domain.repository.MovieRepository
import com.androidacademy.academyapp2020.domain.api.MovieApiService
import com.androidacademy.academyapp2020.domain.api.response.MovieId
import javax.inject.Inject
import javax.inject.Singleton

private const val REPOSITORY_TAG = "repository_tag"

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {

    override suspend fun loadMoviesList(page: Int): List<Movie> {
        val idsList: List<MovieId>
        val moviesList = mutableListOf<Movie>()
        try {
            idsList = movieApiService.getPopularMovies(page).movieIdsList
            idsList.forEach {
                val movie = loadMovieDetails(it.id)
                Log.i(REPOSITORY_TAG, movie.toString())
                moviesList.add(movie)
            }
        } catch (e: Exception) {
            Log.i(REPOSITORY_TAG, e.toString())
        }
        return moviesList
    }

    override suspend fun loadMovieDetails(movieId: Int): Movie =
        movieApiService.getMovieDetailsById(movieId).toMovie()
}