package com.carlostorres.movieapp.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.carlostorres.movieapp.BR
import com.carlostorres.movieapp.model.Movie
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    val formattedDate: String
        @Bindable
        get() {
            val sdf = SimpleDateFormat("YYYY-mm-dd", Locale.getDefault())
            return sdf.format(mMovie.release_date)
        }

    fun setDate(date: Date) {
        mMovie.release_date = date
        notifyPropertyChanged(BR.formattedDate)
    }
}
