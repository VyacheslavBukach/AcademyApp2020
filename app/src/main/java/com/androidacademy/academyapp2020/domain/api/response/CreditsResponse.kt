package com.androidacademy.academyapp2020.domain.api.response

import com.androidacademy.academyapp2020.domain.entity.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast") val castList: List<Actor>
)