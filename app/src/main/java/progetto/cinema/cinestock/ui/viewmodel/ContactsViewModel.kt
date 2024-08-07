package progetto.cinema.cinestock.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import progetto.cinema.cinestock.models.user.User
import progetto.cinema.cinestock.data.UserRepository
import progetto.cinema.cinestock.data.UserDatabase

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = UserDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}
