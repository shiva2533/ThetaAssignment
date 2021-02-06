package com.example.thetatechnolabassignment.home.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.thetatechnotest.home.model.Data

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(data: Data)

    @Delete
    suspend fun deleteUser(data: Data)


    @Query("select * from user_table")
    fun getAllUser(): LiveData<List<Data>>
}