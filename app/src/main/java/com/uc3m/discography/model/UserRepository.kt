package com.uc3m.discography.model

class UserRepository(private val userDAO: UserDAO) {

    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }

}