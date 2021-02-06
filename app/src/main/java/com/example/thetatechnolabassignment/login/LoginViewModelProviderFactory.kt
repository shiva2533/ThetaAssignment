package com.example.thetatechnolabassignment.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thetatechnolabassignment.retrofit.RetrofitInstance

class LoginViewModelProviderFactory(val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }

}