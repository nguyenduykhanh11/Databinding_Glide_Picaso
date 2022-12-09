package com.example.databinding.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.databinding.CustomLoadImageViewModel
import com.example.databinding.R
import com.example.databinding.UserObservable
import com.example.databinding.UserViewModel
import com.example.databinding.databinding.ActivityCustomDataBindingBinding

class CustomDataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomDataBindingBinding
//    private val viewModel: CustomLoadImageViewModel by lazy { ViewModelProvider(this)[CustomLoadImageViewModel::class.java] }

    companion object {
        fun getIntent(context: Context) {
            val intent = Intent(context, CustomDataBindingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_data_binding)
        binding.lifecycleOwner = this
        binding.customLoadImageViewModel = CustomLoadImageViewModel()

    }
}