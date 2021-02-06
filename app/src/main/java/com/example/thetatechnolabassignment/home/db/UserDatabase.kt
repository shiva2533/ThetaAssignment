package com.example.thetatechnolabassignment.home.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thetatechnotest.home.model.Data

@Database(entities = arrayOf(Data::class), version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDataBase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also {
                instance = it
            }
        }

        fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDataBase::class.java,
                "user_db.db"
            ).build()


    }
}