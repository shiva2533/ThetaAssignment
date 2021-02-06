package com.example.thetatechnolabassignment.login

import android.content.Context
import android.content.SharedPreferences
import com.example.thetatechnolabassignment.retrofit.RetrofitInstance

class LoginRepository(val context: Context) {
    suspend fun saveUserCredentials(email: String, password: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()

        sharedPreferencesEditor.apply {
            putString("EMAIL", email)
            putString("PASSWORD", password)
        }.apply()
    }
}