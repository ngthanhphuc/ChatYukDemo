package com.example.chatyukdemo

import android.app.Application
import com.example.chatyukdemo.di.chatModule
import com.google.firebase.database.FirebaseDatabase
import org.koin.core.context.startKoin

class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true)

        startKoin { modules(chatModule) }
    }
}