package progetto.cinema.cinestock.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*entity representing the guest session
* associated with a user to sign in
* */

@Entity(tableName = "guest_session_table")
data class GuestSession(
    @PrimaryKey val username: String,
    val password: String,
    val guestSessionId: String
)