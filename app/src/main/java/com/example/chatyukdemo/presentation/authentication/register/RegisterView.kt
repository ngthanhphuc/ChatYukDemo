package com.example.chatyukdemo.presentation.authentication.register

import com.example.chatyukdemo.data.entity.User

interface RegisterView {
    fun onUsernameEmpty()
    fun onEmailEmpty()
    fun onEmailInvalid()
    fun onPasswordEmpty()
    fun onPasswordToShort()
    fun onConfirmPasswordEmpty()
    fun onConfirmPasswordNotMatch()
    fun onRegisterStart()
    fun onProgress(visibility: Int)
    fun onRegisterSuccess(user: User)
    fun onRegisterFailed(error: String?)
}