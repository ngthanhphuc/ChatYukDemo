package com.example.chatyukdemo.di

import com.example.chatyukdemo.data.repository.authentication.AuthenticationRepository
import com.example.chatyukdemo.data.repository.database.MessageRepository
import com.example.chatyukdemo.data.route.ChatReferences
import com.example.chatyukdemo.presentation.authentication.login.LoginPresenter
import com.example.chatyukdemo.presentation.authentication.register.RegisterPresenter
import com.example.chatyukdemo.presentation.chatroom.ChatRoomPresenter
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val chatModule = module {
    single { ChatReferences() }
    single { FirebaseAuth.getInstance() }

    factory { AuthenticationRepository(get(), get()) }
    factory { MessageRepository(get()) }

    factory { LoginPresenter(get()) }
    factory { RegisterPresenter(get()) }
    factory { ChatRoomPresenter(get()) }
}