package progetto.cinema.cinestock


object MovieRepository {
    private val movies = listOf(
        Movie(1, "Pinocchio", "Pinocchio, un burattino di legno intagliato da Geppetto e trasformato in una marionetta vivente", 10.0, R.drawable.pinocchio)
        // Add more movies as needed
    )

    fun getMovies() = movies

    fun getMovieById(id: Int) = movies.find { it.id == id } ?: throw Exception("Movie not found")
}