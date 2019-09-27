package com.example.chatyukdemo.common

import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class EditTextListener(private val btn: Button) : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
        TransitionManager.beginDelayedTransition(btn.rootView as ViewGroup)
        if (charSequence.trim().isEmpty()) {
            btn.visibility = View.INVISIBLE
        } else {
            btn.visibility = View.VISIBLE
        }
    }

}