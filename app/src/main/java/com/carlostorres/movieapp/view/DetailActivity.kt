package com.carlostorres.movieapp.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.carlostorres.movieapp.R
import com.carlostorres.movieapp.databinding.ActivityDetailBinding
import com.carlostorres.movieapp.databinding.ActivityMainBinding
import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.services.ServicesImpl
import com.carlostorres.movieapp.viewmodel.MovieViewModel
import io.reactivex.observers.DisposableObserver
import io.realm.Realm
import io.realm.kotlin.where

class DetailActivity: AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idMovie = intent.getStringExtra("idMovie")
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.mvm = MovieViewModel(Movie())

        realm = Realm.getDefaultInstance()
        setDetailData(idMovie, binding)
    }

    private fun setDetailData(idMovie: String,
        binding: ActivityDetailBinding) {

        val movieRealm = getMovieRealm(idMovie)
        if (movieRealm == null) {
            ServicesImpl().getMovie(object: DisposableObserver<Movie>() {
                override fun onNext(movie: Movie) {
                    binding.mvm = MovieViewModel(movie)
                    updateDetailInfo(idMovie, movie)
                }

                override fun onError(e: Throwable) {
                    Log.e("MAIN FAILURE:", e.message)
                }

                override fun onComplete() {}
            }, idMovie)
        } else {
            binding.mvm = MovieViewModel(movieRealm)
        }
    }

    private fun getMovieRealm(idMovie: String): Movie? = realm.where<Movie>()
        .equalTo("id", idMovie)
        .and()
        .isNotNull("tagline")
        .findFirst()

    private fun updateDetailInfo(idMovie: String, movie: Movie) = realm.executeTransaction { _ ->
        val movieUpdate = realm.where<Movie>().equalTo("id", idMovie).findFirst()
        movieUpdate?.tagline = movie.tagline
        movieUpdate?.vote_average = movie.vote_average
        movieUpdate?.status = movie.status
    }
}
