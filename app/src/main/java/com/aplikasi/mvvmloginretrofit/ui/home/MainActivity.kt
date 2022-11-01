package com.aplikasi.mvvmloginretrofit.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aplikasi.mvvmloginretrofit.util.SessionManager
import com.aplikasi.mvvmloginretrofit.databinding.ActivityMainBinding
import com.aplikasi.mvvmloginretrofit.ui.auth.LoginScreen

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding!!.root
        setContentView(view)

        sessionManager = SessionManager(this)

        onResume()
        logoutButton()
    }

    private fun logoutButton() {
        _binding?.btnLogout!!.setOnClickListener {
            sessionManager.deleteToken()
            sessionManager.setSession(false)
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        setUser()
        setLoginStatus()
        super.onResume()
    }

    private fun setLoginStatus() {
        if(!sessionManager.getStatusLogin()){
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun setUser() {
        val user = sessionManager.getUser()

        if (user != null) {
            binding?.apply {
                tvName.text = user.name
                tvEmail.text = user.email
            }
        }
    }
}