package com.uc3m.discography.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
    @Query("SELECT * FROM user_table ORDER BY email ASC")
    fun readAll(): LiveData<List<User>>


    //AQUI PROBE VARIOS PERO NOSE
    @Query("SELECT * FROM user_table WHERE email LIKE :email AND password LIKE :pass ")
    suspend fun getUserByEmailAndPassword(email: String, pass: String) : User

    /*
    @Query("SELECT * FROM character_table WHERE email LIKE :email LIMIT 1")
    fun findByEmail(email: String?): LiveData<Char?>?
    */

    @Query("SELECT * FROM user_table WHERE email LIKE :email AND password LIKE :password")
    fun login(email: String?, password: String?): LiveData<List<User>>
}