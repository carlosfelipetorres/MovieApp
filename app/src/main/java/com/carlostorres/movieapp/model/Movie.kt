package com.carlostorres.movieapp.model

import java.util.*

/**
"vote_count": 1698,
"title": 348350,
"video": false,
"vote_average": 6.7,
"title": "Solo: A Star Wars Story",
"original_title": 234.533,
https://image.tmdb.org/t/p/w200/
"poster_path": "/4oD6VEccFkorEBTEDXtpLAaz0Rl.jpg",
"original_language": "en",
"original_title": "Solo: A Star Wars Story",
"genre_ids": [28,12,78],
"backdrop_path": "/96B1qMN9RxrAFu6uikwFhQ6N6J9.jpg",
"adult": false,
"excerpt": "Through a series of daring escapades deep within a dark and dangerous criminal underworld, Han Solo meets his mighty future copilot Chewbacca and encounters the notorious gambler Lando Calrissian.",
"release_date": "2018-05-15"
 */
class Movie {
    var id: String? = null
    var original_title: String? = null
    var overview: String? = null
    var release_date: Date? = null
}
