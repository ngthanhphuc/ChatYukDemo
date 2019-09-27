package com.example.chatyukdemo.presentation.authentication.login


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chatyukdemo.R
import com.example.chatyukdemo.common.InputTextListener
import com.example.chatyukdemo.data.entity.User
import com.example.chatyukdemo.hideSoftInput
import com.example.chatyukdemo.presentation.authentication.AuthenticationPageListener
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : Fragment(), LoginView {

    private lateinit var pageListener: AuthenticationPageListener
    private val loginPresenter by inject<LoginPresenter>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPresenter.attachView(this)

        loginEmail.addTextChangedListener(InputTextListener(inputEmail))
        loginPass.addTextChangedListener(InputTextListener(inputPass))

        txtSignUp.setOnClickListener { pageListener.onRegisterPage() }
        btnSignIn.setOnClickListener {
            val user = User(
                email = loginEmail.text.toString().trim(),
                password = loginPass.text.toString().trim()
            )
            loginPresenter.doLogin(user)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pageListener = context as AuthenticationPageListener
    }

    override fun onEmailEmpty() {
        inputEmail.error = "Enter email"
        loginEmail.requestFocus()
    }

    override fun onEmailInvalid() {
        inputEmail.error = "Invalid email"
        loginPass.requestFocus()
    }

    override fun onPasswordEmpty() {
        inputPass.error = "Enter password"
        loginPass.requestFocus()
    }

    override fun onLoginStart() {
        context?.hideSoftInput(loginEmail)

        btnSignIn.isEnabled = false
        setInputTextEnabled(false)
    }

    private fun setInputTextEnabled(state: Boolean) {
        inputEmail.isEnabled = state
        inputPass.isEnabled = state
    }

    override fun onProgress(visibility: Int) {
        loading.visibility = visibility
    }

    override fun onLoginSuccess(user: User) {
        pageListener.onAuthenticateSuccess(user)
    }

    override fun onLoginFailed(error: String?) {
        btnSignIn.isEnabled = true
        setInputTextEnabled(true)
    }

    override fun onDetach() {
        super.onDetach()
        loginPresenter.detachView()
    }
}
