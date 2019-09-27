package com.example.chatyukdemo.presentation.authentication.login

import com.example.chatyukdemo.data.entity.User

interface LoginView {
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onLoginStart()
    fun onProgress(visibility: Int)
    fun onLoginSuccess(user: User)
    fun onLoginFailed(error: String?)
}