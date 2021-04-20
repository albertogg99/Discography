package com.uc3m.discography

import android.util.Log
import com.uc3m.discography.model.User
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import com.uc3m.discography.util.Hasher

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var user: User
    @Before
    fun setUp(){
        val pass = "1234"
        val hasher = Hasher()
        val salt = hasher.generateSalt(64)
        val hashedPass = hasher.hashPassword(pass, salt)
        val passToStore = ByteArray(salt.size + hashedPass.size)
        for (i in salt.indices){
            passToStore[i] = salt[i]
        }
        for (i in hashedPass.indices){
            passToStore[i+64] = hashedPass[i]
        }
        user = User ("alex@gmail.com","Alejandro","Fernandez",passToStore)

    }
    @Test
    fun loginhashTest() {
        val h = Hasher()
        val salt = ByteArray(64)
        for (i in salt.indices){
            salt[i] = user.password[i]
        }
        val hashedPass = h.hashPassword("1234", salt)
        for (i in hashedPass.indices) {
            assertSame(user.password[i + 64], hashedPass[i])
        }
    }

}