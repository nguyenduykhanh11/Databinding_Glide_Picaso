package com.example.databinding

import android.content.Context
import android.widget.Toast
import com.example.databinding.model.User

class Presenter(private val context: Context) {
    fun onClickInput(user: User){
        Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show()
    }
}