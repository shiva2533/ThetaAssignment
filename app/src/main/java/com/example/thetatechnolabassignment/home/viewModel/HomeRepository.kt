package com.example.thetatechnolabassignment.home.viewModel

import androidx.room.Database
import com.example.thetatechnolabassignment.home.db.UserDataBase
import com.example.thetatechnolabassignment.retrofit.RetrofitInstance

class HomeRepository(val database: UserDataBase) {
    suspend fun getAllUsers(pageNumber: Int) =
        RetrofitInstance.api.getUsers(pageNumber)

}