package com.example.chatyukdemo.presentation.authentication

import com.example.chatyukdemo.data.entity.User

interface AuthenticationPageListener {
    fun onLoginPage()

    fun onRegisterPage()

    fun onAuthenticateSuccess(user: User)
}