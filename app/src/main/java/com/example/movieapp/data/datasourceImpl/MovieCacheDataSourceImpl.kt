package com.example.movieapp.data.datasourceImpl

import com.example.movieapp.data.datasource.MovieCacheDataSource
import com.example.movieapp.data.model.Movie

class MovieCacheDataSourceImpl : MovieCacheDataSource  {
    private var movieList = ArrayList<Movie>()
    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesFromCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}