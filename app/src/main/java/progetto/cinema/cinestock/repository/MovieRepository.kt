package progetto.cinema.cinestock.repository

import android.content.Context
import progetto.cinema.cinestock.models.movie.TMDbMovie
import progetto.cinema.cinestock.network.movie.MovieApi
import progetto.cinema.cinestock.network.search.SearchMovieApi

class MovieRepository(private val context: Context) {

    suspend fun getTrendingMovies(apiKey: String): List<TMDbMovie> {
        return MovieApi.getRetrofitService().getTrendingMovies(apiKey).results
    }

    suspend fun searchMovies(apiKey: String, query: String): List<TMDbMovie> {
        return SearchMovieApi.getRetrofitService().searchMovies(apiKey, query).results
    }

    suspend fun getMovieDetails(apiKey: String, movieId: Int): TMDbMovie {
        return MovieApi.getRetrofitService().getMovieDetails(movieId, apiKey)
    }
}
