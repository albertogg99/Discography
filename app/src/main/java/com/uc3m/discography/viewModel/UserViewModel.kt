package com.uc3m.discography.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uc3m.discography.model.User
import com.uc3m.discography.model.UserDatabase
import com.uc3m.discography.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel (application: Application): AndroidViewModel(application){
    val readAll: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDAO = UserDatabase.getDatabase(application).userDAO()
        repository = UserRepository(userDAO)
        readAll = repository.readAll
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}