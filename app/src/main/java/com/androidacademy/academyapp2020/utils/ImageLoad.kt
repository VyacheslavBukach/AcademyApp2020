package com.androidacademy.academyapp2020.utils

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation

private const val IMAGE_CORNER = 16f

fun ImageView.loadActorPicture(url: String) {
    this.load(url) {
        transformations(RoundedCornersTransformation(IMAGE_CORNER))
    }
}

fun ImageView.loadMoviePicture(url: String) {
    this.load(url) {
        transformations(
            RoundedCornersTransformation(
                topLeft = IMAGE_CORNER,
                topRight = IMAGE_CORNER
            )
        )
    }
}

fun ImageView.loadMovieBackdrop(url: String?) {
    this.load(url)
}