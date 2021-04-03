package com.uc3m.discography.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uc3m.discography.util.Hasher
import com.uc3m.discography.model.User
import com.uc3m.discography.model.UserDatabase
import com.uc3m.discography.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel (application: Application): AndroidViewModel(application){
    private val readAll: LiveData<List<User>>
    private val repository: UserRepository
    private val hasher: Hasher = Hasher()

    init {
        val userDAO = UserDatabase.getDatabase(application).userDAO()
        repository = UserRepository(userDAO)
        readAll = repository.readAll


    }

    fun addUser(email: String, firstName: String, lastName: String, pass: String){
        viewModelScope.launch(Dispatchers.IO) {
            val salt = hasher.generateSalt(64)
            val hashedPass = hasher.hashPassword(pass, salt)
            val passToStore = ByteArray(salt.size + hashedPass.size)
            for (i in salt.indices){
                passToStore[i] = salt[i]
            }
            for (i in hashedPass.indices){
                passToStore[i+64] = hashedPass[i]
            }
            val user = User( email, firstName, lastName, passToStore)
            repository.addUser(user)
        }

    }

    suspend fun findUser(email: String): User{
        return repository.findUser(email)
    }

    suspend fun logUser(email: String, pass: String) : Boolean{
        val user = findUser(email)
        if(user == null){
            Log.d("FailLogin", "Invalid login. User does not exist")
            return false
        }
        else {
            val salt = ByteArray(64)
            for (i in salt.indices){
                salt[i] = user.password[i]
            }
            val hashedPass = hasher.hashPassword(pass, salt)
            for (i in hashedPass.indices){
                if (user.password[i+64] != hashedPass[i]){
                    Log.d("FailLogin", "Invalid login. Password is not correct")
                    return false
                }
            }
            Log.d("SuccessLogin", "User " + user.email + " has logged in correctly")
            return true
        }

    }







}