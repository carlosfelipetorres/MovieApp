package com.carlostorres.movieapp.services

import com.carlostorres.movieapp.model.Movie
import io.reactivex.Observer

interface MovieServices {
    fun getMovies(observer: Observer<List<Movie>>)
    fun getMovie(observer: Observer<Movie>, idMovie: Int)
}