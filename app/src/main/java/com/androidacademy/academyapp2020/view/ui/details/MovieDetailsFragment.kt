package com.androidacademy.academyapp2020.view.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.entity.Movie
import com.androidacademy.academyapp2020.data.repository.MovieRepositoryImpl
import com.androidacademy.academyapp2020.databinding.FragmentMovieDetailsBinding
import com.androidacademy.academyapp2020.network.RetrofitModule
import com.androidacademy.academyapp2020.utils.LoadStatus
import com.androidacademy.academyapp2020.utils.loadMovieBackdrop
import com.androidacademy.academyapp2020.view.adapter.ActorAdapter
import com.androidacademy.academyapp2020.view.adapter.ItemDecorator
import com.androidacademy.academyapp2020.viewmodel.ViewModelFactory

const val ARG_MOVIE = "movie_param"

class MovieDetailsFragment : Fragment() {

    private val repository = MovieRepositoryImpl(RetrofitModule.movieApiService)
    private val viewModel: MovieDetailsViewModel by viewModels { ViewModelFactory(repository) }

    private var movieId: Int = -1

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = requireArguments().getInt(ARG_MOVIE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        initObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.movie.observe(viewLifecycleOwner, this::initMovieDetailsViews)
        viewModel.status.observe(viewLifecycleOwner, this::updateProgressBar)
        viewModel.getMovie(movieId)
    }

    private fun initMovieDetailsViews(movie: Movie?) {
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

    private fun updateProgressBar(status: LoadStatus) {
        when (status) {
            is LoadStatus.Success -> showProgressBar(false)
            is LoadStatus.Loading -> showProgressBar(true)
            is LoadStatus.Error -> showProgressBar(false)
        }
    }

    private fun showProgressBar(loading: Boolean) {
        binding.pbMovieDetails.isVisible = loading
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE, movieId)
                }
            }
    }
}