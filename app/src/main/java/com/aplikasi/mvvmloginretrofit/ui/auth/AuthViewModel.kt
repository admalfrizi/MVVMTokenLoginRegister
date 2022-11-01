package com.aplikasi.mvvmloginretrofit.ui.auth

import androidx.lifecycle.*
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import com.aplikasi.tokenloginretrofit.request.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo : UserRepository
): ViewModel() {

    fun login(body: LoginRequest, isLogin: SessionManager) = repo.login(body, isLogin).asLiveData()

    fun register(body: RegisterRequest, isReg : SessionManager) = repo.register(body, isReg).asLiveData()

}