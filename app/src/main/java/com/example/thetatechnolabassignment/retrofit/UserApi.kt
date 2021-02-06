package com.example.thetatechnolabassignment.retrofit

import com.example.thetatechnotest.home.model.UserDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") pageNumber: Int = 1
    ): Response<UserDetails>
}