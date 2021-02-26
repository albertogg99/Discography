package com.uc3m.discography.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val email: String,
    val name: String,
    val surname: String,
    val password: String
)