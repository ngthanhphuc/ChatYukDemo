package com.example.chatyukdemo.presentation.chatroom.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chatyukdemo.data.entity.Chat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.from_message.*

class HolderMessageFrom(override val containerView: View?) :
    RecyclerView.ViewHolder(containerView as View), LayoutContainer {
    fun bindChatContent(chat: Chat){
        if (chat.isSameUser == true){
            fromUsernameGroup.visibility = View.GONE
        } else {
            fromUsernameGroup.visibility = View.VISIBLE
        }
        fromUsername.text = chat.user
        fromMessage.text = chat.message
    }
}