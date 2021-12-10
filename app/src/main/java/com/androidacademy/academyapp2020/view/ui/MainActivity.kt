package com.androidacademy.academyapp2020.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidacademy.academyapp2020.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, MoviesListFragment())
                commit()
            }
        }
    }
}