package com.uc3m.discography.model

import androidx.lifecycle.LiveData

class UserRepository(private val userDAO: UserDAO) {

    val readAll: LiveData<List<User>> = userDAO.readAll()

    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }

    //val getUserByEmailAndPassword : LiveData<List<User>> = userDAO.getUserByEmailAndPassword()
    //val getUserByEmailAndPassword :User = userDAO.getUserByEmailAndPassword(email, pass)


    suspend fun getUserByEmailAndPassword(email: String, password: String): User{
        return userDAO.getUserByEmailAndPassword(email, password)
    }

    fun login(email: String?, password: String?): LiveData<List<User>> {
        return userDAO.login(email, password)
    }

}