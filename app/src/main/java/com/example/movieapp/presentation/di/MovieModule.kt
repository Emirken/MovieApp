package com.example.movieapp.presentation.di

import com.example.movieapp.domain.usecases.GetMovieUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase
import com.example.movieapp.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMovieUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): ViewModelFactory {

        return ViewModelFactory(
            getMoviesUseCase,
            updateMoviesUseCase
        )
    }
}