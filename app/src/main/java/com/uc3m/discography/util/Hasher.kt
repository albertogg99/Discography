package com.uc3m.discography.util

import android.util.Log
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class Hasher {

    // Inspirado en "Implementing PBKDF2 in Java"
    // de https://www.baeldung.com/java-password-hashing
    fun hashPassword(password: String, salt: ByteArray): ByteArray{

        //KeySpec
        val iterations = 65536 //2^16
        val keyLenght = 64
        val spec = PBEKeySpec(password.toCharArray(), salt, iterations, keyLenght)

        //KeyFactory
        val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
        return secretKeyFactory.generateSecret(spec).encoded

    }


    fun generateSalt(size: Int): ByteArray{
        //Salt aleatorio
        val random = SecureRandom()
        val salt = ByteArray(size)
        random.nextBytes(salt)
        return salt
    }

}