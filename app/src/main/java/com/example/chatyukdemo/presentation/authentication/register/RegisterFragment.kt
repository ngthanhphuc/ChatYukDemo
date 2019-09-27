package com.example.chatyukdemo.presentation.authentication.register


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
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(), RegisterView {

    private lateinit var pageListener: AuthenticationPageListener
    private val registerPresenter by inject<RegisterPresenter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerPresenter.attachView(this)

        regisUser.addTextChangedListener(InputTextListener(inputUsername))
        regisEmail.addTextChangedListener(InputTextListener(inputEmail))
        regisPass.addTextChangedListener(InputTextListener(inputPass))
        regisPassConfirm.addTextChangedListener(InputTextListener(inputPassConfirm))

        txtSignIn.setOnClickListener { pageListener.onLoginPage() }
        btnSignUp.setOnClickListener {

            val user = User(
                username = regisUser.text.toString().trim(),
                email = regisEmail.text.toString().trim(),
                password = regisPass.text.toString().trim(),
                confirmPassword = regisPassConfirm.text.toString().trim()
            )

            registerPresenter.doRegister(user)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pageListener = context as AuthenticationPageListener
    }

    override fun onUsernameEmpty() {
        inputUsername.error = "Enter username"
        regisUser.requestFocus()
    }

    override fun onEmailEmpty() {
        inputEmail.error = "Enter email"
        regisEmail.requestFocus()
    }

    override fun onEmailInvalid() {
        inputEmail.error = "Invalid email"
        regisEmail.requestFocus()
    }

    override fun onPasswordEmpty() {
        inputPass.error = "Enter password"
        regisPass.requestFocus()
    }

    override fun onPasswordToShort() {
        inputPass.error = "Password terlalu pendek"
        regisPass.requestFocus()
    }

    override fun onConfirmPasswordEmpty() {
        inputPassConfirm.error = "Enter konfirmasi password"
        regisPassConfirm.requestFocus()
    }

    override fun onConfirmPasswordNotMatch() {
        inputPassConfirm.error = "Password salah"
        regisPassConfirm.requestFocus()
    }

    override fun onRegisterStart() {
        context?.hideSoftInput(regisEmail)

        btnSignUp.isEnabled = false
        setInputTextEnabled(false)
    }

    override fun onProgress(visibility: Int) {
        loading.visibility = visibility
    }

    override fun onRegisterSuccess(user: User) {
        pageListener.onAuthenticateSuccess(user)
    }

    override fun onRegisterFailed(error: String?) {

        btnSignUp.isEnabled = true
        setInputTextEnabled(true)
    }

    private fun setInputTextEnabled(state: Boolean) {
        inputUsername.isEnabled = state
        inputEmail.isEnabled = state
        inputPass.isEnabled = state
        inputPassConfirm.isEnabled = state
    }

    override fun onDetach() {
        super.onDetach()
        registerPresenter.detachView()
    }


}
