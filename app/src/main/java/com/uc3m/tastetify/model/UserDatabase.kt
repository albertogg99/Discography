package com.uc3m.tastetify.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object{

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{

            synchronized(this){

                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration().build()
                }

                return instance
            }

        }

    }
}