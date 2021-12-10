package com.androidacademy.academyapp2020.domain.repository

import com.androidacademy.academyapp2020.domain.entity.Movie

interface MovieRepository {
    suspend fun loadMoviesList(page: Int): List<Movie>
    suspend fun loadMovieDetails(movieId: Int): Movie
}