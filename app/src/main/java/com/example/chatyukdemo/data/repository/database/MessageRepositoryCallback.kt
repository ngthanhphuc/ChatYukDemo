package com.example.chatyukdemo.data.repository.database

import com.example.chatyukdemo.data.entity.Chat

interface MessageRepositoryCallback {
    fun onMessageComing(chat: Chat)
    fun onMessageUpdate(position: Int, chat: Chat)
    fun onMessageDeleted(position: Int)
}