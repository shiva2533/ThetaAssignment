package com.example.thetatechnolabassignment.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thetatechnolabassignment.util.Resource
import com.example.thetatechnotest.home.model.UserDetails
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(val repository: HomeRepository) : ViewModel() {

    var PAGE_SIZE = 0
    val userData: MutableLiveData<Resource<UserDetails>> = MutableLiveData()
    var pageNumber = 1
    var userDetailResponse: UserDetails? = null

    init {
        getAllUsers()
    }


    fun getAllUsers() = viewModelScope.launch {
        userData.postValue(Resource.Loading())
        val response = repository.getAllUsers(pageNumber)
        userData.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<UserDetails>): Resource<UserDetails> {
        if (response.isSuccessful) {
            response.body()?.let {
                PAGE_SIZE = it.total_pages
                pageNumber++
                if (userDetailResponse == null)
                    userDetailResponse = it
                else {
                    val oldUserDeta = userDetailResponse?.data
                    val newUserDetal = it.data

                    oldUserDeta?.addAll(newUserDetal)

                }
                return Resource.Success(userDetailResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }

    fun removeAllData() = viewModelScope.launch {
        repository.removeAllData()
    }
}