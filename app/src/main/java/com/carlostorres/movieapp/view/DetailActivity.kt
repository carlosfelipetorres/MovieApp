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

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idMovie = intent.getStringExtra("idMovie")
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.mvm = MovieViewModel(Movie())

        ServicesImpl().getMovie(object: DisposableObserver<Movie>() {
            override fun onNext(movie: Movie) {
                binding.mvm = MovieViewModel(movie)
            }

            override fun onError(e: Throwable) {
                Log.e("MAIN FAILURE:", e.message)
            }

            override fun onComplete() {}
        }, idMovie)
    }
}
