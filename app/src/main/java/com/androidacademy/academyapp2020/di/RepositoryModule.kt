package com.androidacademy.academyapp2020.di

import com.androidacademy.academyapp2020.data.repository.MovieRepositoryImpl
import com.androidacademy.academyapp2020.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepositoryImpl(impl: MovieRepositoryImpl): MovieRepository
}