package com.uc3m.discography.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val password: String
)