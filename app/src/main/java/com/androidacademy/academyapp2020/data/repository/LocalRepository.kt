package com.androidacademy.academyapp2020.data.repository

import android.content.Context
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.model.loadMovies

class LocalRepository : IRepository {

    override suspend fun loadMoviesFromRepository(context: Context): List<Movie> =
        loadMovies(context)

    override suspend fun loadMovieFromRepository(context: Context, movieId: Int?): Movie? =
        loadMovies(context).find { movie -> movie.id == movieId }
}