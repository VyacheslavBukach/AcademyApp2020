package com.androidacademy.academyapp2020.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.databinding.ViewHolderMovieBinding
import com.androidacademy.academyapp2020.utils.loadMoviePicture

class MovieAdapter(
    private val listener: OnMovieClickListener
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MoviesCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        viewHolder.bind(getItem(position), listener)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewHolderMovieBinding.bind(view)

        fun bind(movie: Movie, listener: OnMovieClickListener) {
            binding.apply {
                tvMovieTitle.text = movie.title
                // Coil load extension
                ivMoviePoster.loadMoviePicture(movie.poster)
                rbMovie.rating = movie.ratings / 2f
                tvMovieNumberOfRatings.text =
                    itemView.context.getString(R.string.review, movie.numberOfRatings.toString())
                tvMovieAge.text =
                    itemView.context.getString(R.string.age, movie.minimumAge.toString())
                tvMovieRuntime.text =
                    itemView.context.getString(R.string.duration, movie.runtime.toString())
                tvMovieGenres.text = movie.genres.joinToString { it.name }
                itemView.setOnClickListener { listener.onMovieClick(movie) }
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}