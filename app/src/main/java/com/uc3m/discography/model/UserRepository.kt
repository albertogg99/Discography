package com.uc3m.discography.model

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {

    val readAll: LiveData<List<User>> = userDAO.readAll()


    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }

}