package com.androidacademy.academyapp2020.data.repository

import android.content.Context
import com.androidacademy.academyapp2020.data.model.Movie

interface IRepository {
    suspend fun loadMoviesFromRepository(context: Context): List<Movie>
    suspend fun loadMovieFromRepository(context: Context, movieId: Int?): Movie?
}