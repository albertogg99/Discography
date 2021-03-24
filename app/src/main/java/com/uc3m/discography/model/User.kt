package com.uc3m.discography.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey()
    val email: String,
    val name: String,
    val surname: String,
    val password: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (!password.contentEquals(other.password)) return false

        return true
    }

    override fun hashCode(): Int {
        return password.contentHashCode()
    }
}