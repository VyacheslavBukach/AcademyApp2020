package com.androidacademy.academyapp2020.network.response

import com.androidacademy.academyapp2020.data.entity.Genre
import com.androidacademy.academyapp2020.data.entity.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String = "",
    @SerialName("poster_path") val poster: String = "",
    @SerialName("backdrop_path") val backdrop: String = "",
    @SerialName("vote_average") val ratings: Float, // vote_average
    @SerialName("vote_count") val numberOfRatings: Int, // vote_count
    @SerialName("adult") val adult: Boolean, // adult
    @SerialName("runtime") val runtime: Int = 0,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("credits") val actors: CreditsResponse
) {
    fun toMovie() =
        Movie(
            id = id,
            title = title,
            overview = overview,
            poster = "$IMAGE_BASE_URL$POSTER_SIZE$poster",
            backdrop = "$IMAGE_BASE_URL$BACKDROP_SIZE$backdrop",
            ratings = ratings,
            numberOfRatings = numberOfRatings,
            minimumAge = if (adult) 16 else 13,
            runtime = runtime,
            genres = genres,
            actors = actors.castList
                .filter { it.picture.isNotEmpty() }
                .map { it.copy(picture = "$IMAGE_BASE_URL$ACTOR_SIZE${it.picture}") }
        )

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        private const val BACKDROP_SIZE = "w1280"
        private const val POSTER_SIZE = "w342"
        private const val ACTOR_SIZE = "w185"
    }
}


