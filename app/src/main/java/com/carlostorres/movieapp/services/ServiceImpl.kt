package com.carlostorres.movieapp.services

import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.model.MoviesResponse
import io.reactivex.Observer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.coroutineContext

class ServicesImpl: MovieServices {

    companion object {
        private val URL = "https://api.themoviedb.org/3/"
        private val API_KEY = "1b8f4558b040854224cb7c9d7ff98d5a"
        private val NO_CONNECTION_MESSAGE = "Revisa tu conexion a internet"
    }

    private var retrofit: Retrofit? = null
    private var api: MovieApi? = null

    override fun getMovies(observer: Observer<List<Movie>>) {
        setupRetrofit()
        val call = api?.getMovies(API_KEY)

        call?.enqueue(object: Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                response.body()?.results.let {
                    it?.let { it1 -> observer.onNext(it1) }
                    observer.onComplete()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                observer.onError(Exception(NO_CONNECTION_MESSAGE))
            }
        })

    }

    override fun getMovie(observer: Observer<Movie>, idMovie: String) {
        setupRetrofit()
        val call = api?.getMovie(idMovie, API_KEY)

        call?.enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body().let {
                    it?.let { it1 -> observer.onNext(it1) }
                    observer.onComplete()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                observer.onError(Exception(NO_CONNECTION_MESSAGE))
            }
        })

    }

    private fun setupRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.SECONDS)
            .connectTimeout(2, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit?.create<MovieApi>(MovieApi::class.java)
    }
}
