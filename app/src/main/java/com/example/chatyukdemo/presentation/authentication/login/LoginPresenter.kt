package com.example.chatyukdemo.presentation.authentication.login

import android.view.View
import com.example.chatyukdemo.data.entity.User
import com.example.chatyukdemo.data.repository.authentication.AuthenticationRepository
import com.example.chatyukdemo.data.repository.authentication.AuthenticationRepositoryCallback
import com.example.chatyukdemo.isEmailValid

class LoginPresenter(private val authenticationRepository: AuthenticationRepository) {

    private var view: LoginView? = null

    fun attachView(view: LoginView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun doLogin(user: User) {
        if (user.email.isNullOrEmpty()) {
            view?.onEmailEmpty()
            return
        }

        if (user.email?.isEmailValid() == false) {
            view?.onEmailInvalid()
            return
        }

        if (user.password.isNullOrEmpty()) {
            view?.onPasswordEmpty()
            return
        }

        view?.onLoginStart()
        view?.onProgress(View.GONE)

        authenticationRepository.doLogin(user, object : AuthenticationRepositoryCallback {
            override fun onSuccess(user: User) {
                view?.onLoginSuccess(user)
                view?.onProgress(View.VISIBLE)
            }

            override fun onFailed(error: String?) {
                view?.onLoginFailed(error)
                view?.onProgress(View.VISIBLE)
            }
        })
    }
}