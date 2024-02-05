package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.usecases.GetMovieUseCase
import com.example.movieapp.domain.usecases.UpdateMoviesUseCase

class ViewModelFactory(
    private val getMovieUseCase: GetMovieUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.example.movieapp.presentation.MyViewModel(getMovieUseCase,updateMoviesUseCase) as T
    }
}