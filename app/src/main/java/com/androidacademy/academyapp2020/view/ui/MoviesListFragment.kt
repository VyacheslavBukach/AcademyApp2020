package com.androidacademy.academyapp2020.view.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.repository.LocalRepository
import com.androidacademy.academyapp2020.databinding.FragmentMoviesListBinding
import com.androidacademy.academyapp2020.utils.LoadStatus
import com.androidacademy.academyapp2020.view.adapter.ItemDecorator
import com.androidacademy.academyapp2020.view.adapter.MovieAdapter
import com.androidacademy.academyapp2020.viewmodel.MoviesListViewModel
import com.androidacademy.academyapp2020.viewmodel.ViewModelFactory

class MoviesListFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    private val repository = LocalRepository()
    private val viewModel: MoviesListViewModel by viewModels { ViewModelFactory(repository) }

    private val movieAdapter = MovieAdapter(this)

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        initMovieRecyclerView()
        viewModel.moviesList.observe(viewLifecycleOwner, this::updateMovieAdapter)
        viewModel.status.observe(viewLifecycleOwner, this::updateProgressBar)
        viewModel.getMovies(requireContext())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMovieRecyclerView() {
        binding.rvMovies.apply {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    layoutManager = GridLayoutManager(context, 4)
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    layoutManager = GridLayoutManager(context, 2)
                }
            }
            adapter = movieAdapter
            addItemDecoration(ItemDecorator(left = 6, right = 6, bottom = 6, top = 6))
        }
    }

    private fun updateMovieAdapter(movies: List<Movie>) {
        movieAdapter.submitList(movies)
    }

    private fun updateProgressBar(status: LoadStatus) {
        when (status) {
            is LoadStatus.Success -> showProgressBar(false)
            is LoadStatus.Loading -> showProgressBar(true)
            is LoadStatus.Error -> showProgressBar(false)
        }
    }

    private fun showProgressBar(loading: Boolean) {
        binding.pbMoviesList.isVisible = loading
    }

    override fun onMovieClick(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, MovieDetailsFragment.newInstance(movieId))
            addToBackStack(null)
            commit()
        }
    }
}