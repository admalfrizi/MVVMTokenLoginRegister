package com.aplikasi.mvvmloginretrofit.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aplikasi.mvvmloginretrofit.api.State
import com.aplikasi.mvvmloginretrofit.databinding.ActivityLoginScreenBinding
import com.aplikasi.mvvmloginretrofit.repository.UserRepository
import com.aplikasi.mvvmloginretrofit.ui.home.MainActivity
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.tokenloginretrofit.request.LoginRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginScreen() : AppCompatActivity() {

    private val loginviewModel: AuthViewModel by viewModels()
    private var binding : ActivityLoginScreenBinding? = null
    private val _binding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)
        val sessionManager = SessionManager(this)


        if(sessionManager.getStatusLogin()){
            startActivity(Intent(this, MainActivity::class.java))
        }
        else {
            _binding.loginBtn.setOnClickListener {
                observeViewState(sessionManager)
            }
        }

        _binding.toSignup.setOnClickListener {
            Intent(this, RegisterScreen::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

    private fun observeViewState(sessionManager : SessionManager) {

        val body = LoginRequest(_binding.emailEdt.text.toString(), _binding.pwEdt.text.toString())

        loginviewModel.login(body, sessionManager).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    Toast.makeText(
                        applicationContext,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Intent(applicationContext, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }
                State.ERROR -> {
                    _binding.ld.visibility = View.GONE
                    Toast.makeText(
                        applicationContext,
                        "error : " + it.message,
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("TAG", "Error : " + it.message)
                }
                State.LOADING -> {
                    _binding.ld.visibility = View.VISIBLE
                }
            }
        }
    }
}