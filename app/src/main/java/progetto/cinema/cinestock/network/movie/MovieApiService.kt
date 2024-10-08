package progetto.cinema.cinestock.network.movie

import progetto.cinema.cinestock.models.movie.TMDbMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String
    ): TMDbResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): TMDbMovie
}

data class TMDbResponse(
    val results: List<TMDbMovie>
)
