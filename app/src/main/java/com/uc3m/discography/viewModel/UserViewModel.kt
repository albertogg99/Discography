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
    //val getUserByEmailAndPasswordAuthentication
    private val repository: UserRepository
    //val getUserByEmailAndPassword : User
    //lateinit var user: User


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

    fun getUserByEmailAndPassword(email: String, pass: String) : User{
        //var user : User
        lateinit var user: User
        viewModelScope.launch(Dispatchers.IO) {
            user = repository.getUserByEmailAndPassword(email, pass)

        }
        return user

    }

    fun login(email: String?, password: String?): LiveData<List<User>> {
        return repository.login(email, password)
    }


}