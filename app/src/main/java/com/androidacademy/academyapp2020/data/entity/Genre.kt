package com.androidacademy.academyapp2020.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Genre(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
) : Parcelable