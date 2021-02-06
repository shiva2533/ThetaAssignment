package com.example.thetatechnolabassignment.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thetatechnolabassignment.util.Resource
import com.example.thetatechnotest.home.model.UserDetails
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel (val repository: LoginRepository):ViewModel(){
    fun saveCredentials(email: String, password: String) = viewModelScope.launch {
        repository.saveUserCredentials(email, password)
    }
}