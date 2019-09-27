package com.example.chatyukdemo.data.repository.authentication

import com.example.chatyukdemo.data.entity.User

interface AuthenticationRepositoryCallback {
    fun onSuccess(user: User)
    fun onFailed(error: String?)
}