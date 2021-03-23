package com.uc3m.discography.util

import java.security.MessageDigest

class Hasher {
    //Funcion hashString que realiza el hasheo dandole un String y un algoritmo
    private fun hashString(input: String, algorithm: String): String {
        return MessageDigest
                .getInstance(algorithm)
                .digest(input.toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) })
    }

    //Funcion para llamar a hashString con el algoritmo MD5
    fun md5(input: String): String {
        return hashString(input, "MD5")
    }

    //Funcion para llamar a hashString con el algoritmo SHA-256
    fun sha256(input: String): String {
        return hashString(input, "SHA-256")
    }

    //Funcion para llamar a hashString con el algoritmo SHA-256
    fun pbkdf2(input: String): String {
        return hashString(input, "PBKDF2WithHmacSHA512")
    }

}