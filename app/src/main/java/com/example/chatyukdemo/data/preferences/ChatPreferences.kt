package com.example.chatyukdemo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.chatyukdemo.common.Constant
import com.example.chatyukdemo.data.entity.User

class ChatPreferences {
    companion object {
        private lateinit var preferences: SharedPreferences

        fun initPreferences(context: Context): ChatPreferences {
            preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            return ChatPreferences()
        }
    }

    var userInfo: User
        get() {
            val user = User()
            user.username = preferences.getString(Constant.PREF_USERNAME, "")
            user.email = preferences.getString(Constant.PREF_EMAIL, "")
            user.userId = preferences.getString(Constant.PREF_USER_ID, "")
            return user
        }
        set(modelUser) {
            val editor = preferences.edit()
            editor.putString(Constant.PREF_USERNAME, modelUser.username)
            editor.putString(Constant.PREF_EMAIL, modelUser.email)
            editor.putString(Constant.PREF_USER_ID, modelUser.userId)
            editor.apply()
        }
}