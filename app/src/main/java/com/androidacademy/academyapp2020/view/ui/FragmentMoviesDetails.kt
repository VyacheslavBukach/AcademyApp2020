package com.androidacademy.academyapp2020.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.databinding.FragmentMoviesDetailsBinding
import com.androidacademy.academyapp2020.utils.loadMovieBackdrop
import com.androidacademy.academyapp2020.view.adapter.ActorAdapter
import com.androidacademy.academyapp2020.view.adapter.ItemDecorator

const val ARG_MOVIE = "movie_param"

class FragmentMoviesDetails : Fragment() {

    private var movie: Movie? = null

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable(ARG_MOVIE)
        Log.i("FragmentMoviesList", movie.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieViews()
        initActorRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMovieViews() {
        binding.apply {
            movie?.let {
                if (it.actors.isEmpty()) tvMovieDetailsCast.visibility = View.GONE
                tvMovieDetailsAge.text = getString(R.string.age, it.minimumAge.toString())
                tvMovieDetailsTitle.text = it.title
                ivMovieDetailsBackdrop.loadMovieBackdrop(it.backdrop)
                tvMovieDetailsGenres.text = it.genres.joinToString { genre -> genre.name }
                rbMovieDetails.rating = it.ratings / 2f
                tvMovieDetailsNumberOfRatings.text =
                    getString(R.string.review, it.numberOfRatings.toString())
                tvMovieDetailsOverview.text = it.overview
            }
        }
    }

    private fun initActorRecyclerView() {
        binding.apply {
            rvActors.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ActorAdapter(movie!!.actors)
                addItemDecoration(ItemDecorator(left = 4, right = 4))
                setHasFixedSize(true)
            }
            tvMovieDetailsBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) =
            FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MOVIE, movie)
                }
            }
    }
}