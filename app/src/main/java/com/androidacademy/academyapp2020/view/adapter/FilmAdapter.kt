package com.androidacademy.academyapp2020.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.academyapp2020.data.model.Film
import com.androidacademy.academyapp2020.R

class FilmAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FilmAdapter.FilmHolder>() {

    private val filmList = listOf(
        Film(
            13,
            R.drawable.ic_like_no,
            R.drawable.movie_1_poster,
            R.string.film_tag_1,
            4,
            125,
            R.string.film_title_1,
            137
        ),
        Film(
            16,
            R.drawable.ic_like_yes,
            R.drawable.movie_2_poster,
            R.string.film_tag_2,
            5,
            98,
            R.string.film_title_2,
            97
        ),
        Film(
            13,
            R.drawable.ic_like_no,
            R.drawable.movie_3_poster,
            R.string.film_tag_3,
            4,
            38,
            R.string.film_title_3,
            102
        ),
        Film(
            13,
            R.drawable.ic_like_no,
            R.drawable.movie_4_poster,
            R.string.film_tag_4,
            5,
            74,
            R.string.film_title_4,
            120
        ),
    )

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder =
        FilmHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(filmList[position], listener)
    }

    override fun getItemCount(): Int = filmList.size

    class FilmHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(film: Film, listener: OnItemClickListener) {
            itemView.findViewById<TextView>(R.id.tv_age).text =
                itemView.context.getString(R.string.age, film.ageRate.toString())
            itemView.findViewById<ImageView>(R.id.iv_like).setImageResource(film.isLike)
            itemView.findViewById<ImageView>(R.id.iv_poster).setImageResource(film.imageUrl)
            itemView.findViewById<TextView>(R.id.tv_tag).text =
                itemView.context.getText(film.tag)
            itemView.findViewById<TextView>(R.id.tv_review).text =
                itemView.context.getString(R.string.review, film.numberOfReviews.toString())
            itemView.findViewById<TextView>(R.id.tv_film_name).text =
                itemView.context.getText(film.title)
            itemView.findViewById<TextView>(R.id.tv_duration).text =
                itemView.context.getString(R.string.duration, film.duration.toString())
            itemView.setOnClickListener { listener.onItemClick() }
        }
    }
}