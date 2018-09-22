package com.carlostorres.movieapp.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.carlostorres.movieapp.R
import com.carlostorres.movieapp.databinding.ActivityMainBinding
import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.services.ServicesImpl
import io.reactivex.observers.DisposableObserver

class MainActivity : AppCompatActivity(), MovieAdapter.IMovieClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        binding.movieList.layoutManager = layoutManager

        ServicesImpl().getMovies(object: DisposableObserver<List<Movie>>() {
            override fun onNext(movieList: List<Movie>) {
                val adapter = MovieAdapter(movieList, this@MainActivity)
                binding.movieList.adapter = adapter
            }

            override fun onError(e: Throwable) {
                Log.e("MAIN FAILURE:", e.message)
            }

            override fun onComplete() {}
        })
    }

    override fun movieClicked(idMovie: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idMovie", idMovie)
        startActivity(intent)
    }
}
