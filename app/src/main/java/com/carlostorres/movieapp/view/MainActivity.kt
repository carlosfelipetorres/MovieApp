package com.carlostorres.movieapp.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.carlostorres.movieapp.R
import com.carlostorres.movieapp.databinding.ActivityMainBinding
import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.services.ServicesImpl
import io.reactivex.observers.DisposableObserver
import io.realm.Realm
import io.realm.Realm.deleteRealm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import io.realm.kotlin.createObject
import io.realm.kotlin.where


class MainActivity : AppCompatActivity(), MovieAdapter.IMovieClickListener {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRealm()

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        binding.movieList.layoutManager = layoutManager

        val movieListRealm = realm.where<Movie>().findAll()
        if (movieListRealm.isEmpty()) {
            ServicesImpl().getMovies(object: DisposableObserver<List<Movie>>() {
                override fun onNext(movieList: List<Movie>) {
                    setMovieList(movieList, binding)
                    saveDataRealm(movieList)
                }

                override fun onError(e: Throwable) {
                    Log.e("MAIN FAILURE:", e.message)
                }

                override fun onComplete() {}
            })
        } else {
            setMovieList(movieListRealm, binding)
        }
    }

    private fun setMovieList(movieList: List<Movie>, binding: ActivityMainBinding) {
        val adapter = MovieAdapter(movieList, this@MainActivity)
        binding.movieList.adapter = adapter
    }

    override fun movieClicked(idMovie: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idMovie", idMovie)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun initRealm() {
        Realm.init(applicationContext)
        try {
            realm = Realm.getDefaultInstance()
        } catch (r: RealmMigrationNeededException) {
            val conf = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
            deleteRealm(conf.build())
            realm = Realm.getDefaultInstance()
        }
    }

    private fun saveDataRealm(movieList: List<Movie>) {
        realm.executeTransactionAsync(Realm.Transaction { bgRealm ->
            movieList.forEach {
                val movie = bgRealm.where<Movie>().equalTo("id", it.id).findFirst()
                if (movie == null) {
                    val mov = bgRealm.createObject<Movie>(it.id)
                    mov.original_title = it.original_title
                    mov.overview = it.overview
                    mov.poster_path = it.poster_path
                    mov.release_date = it.release_date
                    mov.tagline = it.tagline
                    mov.vote_average = it.vote_average
                }
            }
        }, Realm.Transaction.OnSuccess {
            val movieR = realm.where<Movie>().findAll()
            movieR.forEach {
                Log.e("-----", it.original_title)
            }
        })
    }
}
