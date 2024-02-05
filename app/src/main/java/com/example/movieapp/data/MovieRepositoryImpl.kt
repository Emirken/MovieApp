package com.example.movieapp.data

import com.example.movieapp.data.datasource.MovieCacheDataSource
import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
): MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }



    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesFromCache(newListOfMovies)
        return newListOfMovies

    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null){
                movieList = body.movies
            }

        }catch (exception: java.lang.Exception){

        }
        return movieList

    }



    suspend fun getMoviesFromROOM(): List<Movie>{
        var movieList: List<Movie> = emptyList()  // Initialize with an empty list
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (exception: Exception){

        }
        if (movieList.isNotEmpty()){
            return movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)

        }
        return movieList

    }



    private suspend fun getMoviesFromCache(): List<Movie>? {
        lateinit var movieList : List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        }catch (exception : Exception){

        }
        if (movieList.size > 0 ){
            return movieList
        }else{
            movieList = getMoviesFromROOM()
            movieCacheDataSource.saveMoviesFromCache(movieList)
        }
        return movieList






    }
}