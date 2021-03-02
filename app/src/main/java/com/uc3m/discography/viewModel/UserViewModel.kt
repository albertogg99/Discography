package com.uc3m.discography.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uc3m.discography.model.Hasher
import com.uc3m.discography.model.User
import com.uc3m.discography.model.UserDatabase
import com.uc3m.discography.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel (application: Application): AndroidViewModel(application){
    val readAll: LiveData<List<User>>
    private val repository: UserRepository
    private val hasher: Hasher = Hasher()

    init {
        val userDAO = UserDatabase.getDatabase(application).userDAO()
        repository = UserRepository(userDAO)
        readAll = repository.readAll


    }

    fun addUser(email: String, firstName: String, lastName: String, pass: String){
        viewModelScope.launch(Dispatchers.IO) {
            val hashedPass = hasher.sha256(pass)
            val user = User( email, firstName, lastName, hashedPass)
            repository.addUser(user)
        }

    }

    private suspend fun findUser(email: String): User{
        return repository.findUser(email)
    }

    suspend fun logUser(email: String, pass: String) : Boolean{
        val user = findUser(email)
        if(user == null){
            Log.d("FailLogin", "Invalid login. User does not exist")
            return false
        }
        else if (user.password == hasher.sha256(pass)) {
            Log.d("SuccessLogin", "User " + user.email + " has logged in correctly")
            return true
        }
        Log.d("FailLogin", "Invalid login. Password is not correct")
        return false

    }







}