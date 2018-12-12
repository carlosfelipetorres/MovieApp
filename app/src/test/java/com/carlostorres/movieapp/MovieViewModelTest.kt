package com.carlostorres.movieapp

import com.carlostorres.movieapp.model.Movie
import com.carlostorres.movieapp.viewmodel.MovieViewModel
import org.junit.Assert.assertEquals

import org.junit.Test


class MovieViewModelTest {
    var viewModel: MovieViewModel? = MovieViewModel(Movie())

    @Test
    fun testTitle() {
        viewModel?.title = "dummy text"
        assertEquals("dummy text", viewModel?.title)
    }

    @Test
    fun testDate() {
        viewModel?.date = "2018-09-23"
        assertEquals("2018-09-23", viewModel?.date)
    }

    @Test
    fun testId() {
        viewModel?.idMovie = "1"
        assertEquals("1", viewModel?.idMovie)
    }

    @Test
    fun testOverview() {
        viewModel?.overview = "dummy text"
        assertEquals("dummy text", viewModel?.overview)
    }

    @Test
    fun testPoster() {
        viewModel?.poster = "dummy text"
        assertEquals("dummy text", viewModel?.poster)
    }

    @Test
    fun testStatus() {
        viewModel?.status = "release"
        assertEquals("release", viewModel?.status)
    }

    @Test
    fun testTagline() {
        viewModel?.tagline = "dummy text"
        assertEquals("dummy text", viewModel?.tagline)
    }

    @Test
    fun testVoteAverage() {
        viewModel?.voteAverage = "6.7"
        assertEquals("6.7", viewModel?.voteAverage)
    }

    @Test
    fun testTest() {
        assertEquals(4, 3 + 2)
    }

}