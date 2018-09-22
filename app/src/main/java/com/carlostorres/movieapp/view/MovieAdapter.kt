package com.carlostorres.movieapp.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carlostorres.movieapp.R
import com.carlostorres.movieapp.databinding.RowMovieBinding
import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.viewmodel.MovieViewModel

class MovieAdapter(private val mMovies: List<Movie>, val iMovieClickListener: IMovieClickListener) : RecyclerView.Adapter<MovieAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<RowMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.row_movie, parent, false)

        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val binding = holder.binding
        binding.mvm = MovieViewModel(mMovies[position])
        binding.mainLayout.setOnClickListener(View.OnClickListener {
            mMovies[position].id?.let { iMovieClickListener.movieClicked(it) }
        })
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    class BindingHolder(val binding: RowMovieBinding) : RecyclerView.ViewHolder(binding.mainLayout)

    interface IMovieClickListener {
        fun movieClicked(idMovie: String)
    }
}
