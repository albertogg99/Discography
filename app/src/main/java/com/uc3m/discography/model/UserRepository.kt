package com.uc3m.discography.model

import android.provider.ContactsContract
import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {

    val readAll: LiveData<List<User>> = userDAO.readAll()

    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }

    suspend fun findUser(email: String): User{
       return userDAO.findUser(email)
    }

}