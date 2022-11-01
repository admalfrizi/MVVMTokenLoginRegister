package com.aplikasi.mvvmloginretrofit.api

import com.aplikasi.mvvmloginretrofit.util.Constants
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import com.aplikasi.tokenloginretrofit.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RoutesApi {

    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequest
    ): Response<UserResponse>

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body regRequest: RegisterRequest
    ): Response<UserResponse>
}