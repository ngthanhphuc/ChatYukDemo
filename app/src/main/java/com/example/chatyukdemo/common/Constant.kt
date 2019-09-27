package com.example.chatyukdemo.common

import java.text.SimpleDateFormat
import java.util.*

object Constant {
    const val PREF_USERNAME = "user"
    const val PREF_USER_ID = "id"
    const val PREF_EMAIL = "email"

    val time: String
        get() = SimpleDateFormat("dd MMM yyyy , HH.mm", Locale.getDefault()).format(Date())
}