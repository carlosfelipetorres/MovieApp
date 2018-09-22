package com.carlostorres.movieapp.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.carlostorres.movieapp.BR
import com.carlostorres.movieapp.model.Movie

class MovieViewModel(private val mMovie: Movie) : BaseObservable() {

    var title: String?
        @Bindable
        get() = mMovie.original_title
        set(id) {
            mMovie.original_title = id
            notifyPropertyChanged(BR.title)
        }

    var idMovie: String?
        @Bindable
        get() = mMovie.id
        set(subtitle) {
            mMovie.id = subtitle
            notifyPropertyChanged(BR.idMovie)
        }

    var overview: String?
        @Bindable
        get() = mMovie.overview
        set(overview) {
            mMovie.overview = overview
            notifyPropertyChanged(BR.overview)
        }

    var date: String?
        @Bindable
        get() = mMovie.release_date
        set(date) {
            mMovie.release_date = date
            notifyPropertyChanged(BR.date)
        }

    var tagline: String?
        @Bindable
        get() = mMovie.tagline
        set(tagline) {
            mMovie.tagline = tagline
            notifyPropertyChanged(BR.tagline)
        }

    var status: String?
        @Bindable
        get() = mMovie.status
        set(status) {
            mMovie.status = status
            notifyPropertyChanged(BR.status)
        }

    var voteAverage: String?
        @Bindable
        get() = mMovie.vote_average
        set(voteAverage) {
            mMovie.vote_average = voteAverage
            notifyPropertyChanged(BR.voteAverage)
        }

    var poster: String?
        @Bindable
        get() = mMovie.poster_path
        set(poster) {
            mMovie.poster_path = poster
            notifyPropertyChanged(BR.poster)
        }
}
