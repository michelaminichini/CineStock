package progetto.cinema.cinestock.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import progetto.cinema.cinestock.R
import progetto.cinema.cinestock.models.movie.TMDbMovie
import progetto.cinema.cinestock.network.MovieApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RiepilogoActivity : AppCompatActivity() {

    private lateinit var priceTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var proceedButton: Button
    //private var movieId: Int = -1
    private var movieId: Int? = null

    private val apiKey = "e96d473555668ee67739012c7f140604"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApiService = retrofit.create<MovieApiService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riepilogo)

        priceTextView = findViewById(R.id.price_text_view)
        titleTextView = findViewById(R.id.title_text_view)
        descriptionTextView = findViewById(R.id.description_text_view)
        proceedButton = findViewById(R.id.proceed_button)

        // get the film ID from the intent
        movieId = intent.getIntExtra("MOVIE_ID", -1)
        Log.d("RiepilogoActivity", "Received movie ID: $movieId")

        // use a temporary variable for verification
        //val currentMovieId = movieId ?: -1 // used the Elvis operator (?:) to assign a default value (-1) to currentMovieId if movieId is null. In this way, currentMovieId will always be of type Int

//        if (movieId != -1) {
//            fetchMovieDetails(movieId)
//        } else {
//            Toast.makeText(this, "No movie ID provided", Toast.LENGTH_SHORT).show()
//            finish() // closes the activity if filmID is not provided
//        }

        if (movieId != null && movieId != -1) {
            movieId?.let {
                fetchMovieDetails(it)
            }
        } else {
            Toast.makeText(this, "No movie ID provided", Toast.LENGTH_SHORT).show()
            finish()
        }

        proceedButton.setOnClickListener {
            // manage purchase here
            Toast.makeText(this, "Proceeding with purchase...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movieDetails = movieApiService.getMovieDetails(movieId, apiKey)
                runOnUiThread {
                    titleTextView.text = movieDetails.original_title
                    descriptionTextView.text = movieDetails.overview
                    priceTextView.text = "Price: $6.99" // fixed price
                }
            } catch (e: HttpException) {
                runOnUiThread {
                    Toast.makeText(this@RiepilogoActivity, "Failed to fetch movie details: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@RiepilogoActivity, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
