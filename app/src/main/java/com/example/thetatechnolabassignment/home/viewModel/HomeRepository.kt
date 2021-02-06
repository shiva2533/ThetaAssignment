package com.example.thetatechnolabassignment.home.viewModel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import com.example.thetatechnolabassignment.home.db.UserDataBase
import com.example.thetatechnolabassignment.retrofit.RetrofitInstance

class HomeRepository(val context: Context, val database: UserDataBase) {
    suspend fun getAllUsers(pageNumber: Int) =
        RetrofitInstance.api.getUsers(pageNumber)

    suspend fun removeAllData() {
        val sharedPreferences =
            context.getSharedPreferences("sharedPrefs", AppCompatActivity.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences?.edit()
        sharedPreferencesEditor?.apply {
            remove("sharedPrefs")
            clear()
            apply()
        }
    }

}