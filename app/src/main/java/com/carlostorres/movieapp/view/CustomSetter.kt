package com.carlostorres.movieapp.view

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view).load("https://image.tmdb.org/t/p/w185/$url").into(view)
}