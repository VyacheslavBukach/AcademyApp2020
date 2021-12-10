package com.androidacademy.academyapp2020.network

import com.androidacademy.academyapp2020.network.response.MovieDetailsResponse
import com.androidacademy.academyapp2020.network.response.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") append: String = "credits"
    ): MovieDetailsResponse
}