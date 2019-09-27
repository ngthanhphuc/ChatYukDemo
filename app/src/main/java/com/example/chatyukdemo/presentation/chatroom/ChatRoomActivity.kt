package com.example.chatyukdemo.presentation.chatroom

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatyukdemo.R
import com.example.chatyukdemo.common.Constant
import com.example.chatyukdemo.common.EditTextListener
import com.example.chatyukdemo.data.entity.Chat
import com.example.chatyukdemo.data.preferences.ChatPreferences
import com.example.chatyukdemo.presentation.LauncherActivity
import com.example.chatyukdemo.presentation.chatroom.adapter.AdapterMessage
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chat_room.*
import org.koin.android.ext.android.inject
import java.util.*

class ChatRoomActivity : AppCompatActivity(), ChatRoomView {

    private lateinit var adapterMessage: AdapterMessage

    private val chatRoomPresenter by inject<ChatRoomPresenter>()

    private var listChat: MutableList<Chat> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        val user = ChatPreferences.initPreferences(this).userInfo
        toolbar.title = "Chat Chat"

        setSupportActionBar(toolbar)
        etMessage.addTextChangedListener(EditTextListener(btnSend))

        adapterMessage = AdapterMessage(this, listChat)

        with(rvChat) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context).apply { stackFromEnd = true }
            adapter = adapterMessage
        }

        btnSend.setOnClickListener {
            val username = user.username
            val message = etMessage?.text.toString()
            val time = Constant.time

            val chat = Chat()
            chat.user = username
            chat.message = message
            chat.time = time

            chatRoomPresenter.sendMessage(chat)
            etMessage?.setText("")
        }

        chatRoomPresenter.attachView(this)
        chatRoomPresenter.getMessages()
    }

    override fun onMessageUpdate(position: Int, chat: Chat) {
        listChat[position] = chat
        adapterMessage.notifyItemChanged(position, chat)
        rvChat.smoothScrollToPosition(listChat.size)
    }

    override fun onMessageDeleted(position: Int) {
        listChat.removeAt(position)
        adapterMessage.notifyItemRemoved(position)
        rvChat.smoothScrollToPosition(listChat.size)
    }

    override fun onMessageComing(chat: Chat) {
        listChat.add(chat)
        adapterMessage.notifyItemInserted(listChat.lastIndex)
        rvChat.smoothScrollToPosition(listChat.size)
    }

    override fun onDestroy() {
        super.onDestroy()
        chatRoomPresenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_chat, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Sign out")
            builder.setMessage("Do you want to log out?")
            builder.setPositiveButton("YES") { _, _ ->
                val auth = FirebaseAuth.getInstance()
                auth.signOut()

                startActivity(Intent(this, LauncherActivity::class.java))
                finish()
            }
            builder.setNegativeButton("NO", null)
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }
}
