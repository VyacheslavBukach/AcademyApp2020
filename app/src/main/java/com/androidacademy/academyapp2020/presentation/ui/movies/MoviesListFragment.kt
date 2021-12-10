package com.androidacademy.academyapp2020.view.ui.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.databinding.FragmentMoviesListBinding
import com.androidacademy.academyapp2020.domain.entity.Movie
import com.androidacademy.academyapp2020.presentation.adapter.ItemDecorator
import com.androidacademy.academyapp2020.presentation.adapter.MovieAdapter
import com.androidacademy.academyapp2020.presentation.viewmodel.MoviesListViewModel
import com.androidacademy.academyapp2020.utils.LoadStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(R.layout.fragment_movies_list),
    MovieAdapter.OnMovieClickListener {

    private val binding: FragmentMoviesListBinding by viewBinding()
    private val viewModel: MoviesListViewModel by viewModels()
    private val movieAdapter = MovieAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieRecyclerView()
        initObservers()
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

    private fun initObservers() {
        viewModel.status.observe(viewLifecycleOwner, this::updateProgressBar)
        viewModel.getMovies().observe(viewLifecycleOwner, this::updateMovieAdapter)
    }

    private fun updateMovieAdapter(movies: PagedList<Movie>?) {
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
        val action =
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}