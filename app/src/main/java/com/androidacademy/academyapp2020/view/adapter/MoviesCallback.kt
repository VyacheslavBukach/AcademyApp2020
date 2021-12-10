package com.androidacademy.academyapp2020.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.androidacademy.academyapp2020.data.model.Movie

class MoviesCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}