package com.carlostorres.movieapp.services

import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String, @Query("page") page: Int? = 1): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovie(@Query("api_key") apiKey: String, @Field("movie_id") idMovie: Int): Call<Movie>
}