package com.example.chatyukdemo.presentation.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatyukdemo.R
import com.example.chatyukdemo.data.entity.User
import com.example.chatyukdemo.data.preferences.ChatPreferences
import com.example.chatyukdemo.presentation.authentication.login.LoginFragment
import com.example.chatyukdemo.presentation.authentication.register.RegisterFragment
import com.example.chatyukdemo.presentation.chatroom.ChatRoomActivity
import com.example.chatyukdemo.replaceFragment

class AuthenticationActivity : AppCompatActivity(), AuthenticationPageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        if (savedInstanceState == null) {
            replaceFragment(R.id.containerAuth, LoginFragment())
        }
    }

    override fun onLoginPage() = replaceFragment(R.id.containerAuth, LoginFragment())

    override fun onRegisterPage() = replaceFragment(R.id.containerAuth, RegisterFragment())

    override fun onAuthenticateSuccess(user: User) {
        ChatPreferences.initPreferences(this).userInfo = user
        startActivity(Intent(this@AuthenticationActivity, ChatRoomActivity::class.java))
        finish()
    }
}
