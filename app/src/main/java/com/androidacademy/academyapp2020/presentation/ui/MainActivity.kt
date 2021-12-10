package com.androidacademy.academyapp2020.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.view.ui.movies.MoviesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, MoviesListFragment())
                commit()
            }
        }
    }
}