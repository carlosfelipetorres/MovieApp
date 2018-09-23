package com.carlostorres.movieapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Movie(
    @PrimaryKey var id: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var poster_path: String? = "",
    var vote_average: String? = null,
    var tagline: String? = null,
    var status: String?= null) : RealmObject()
