package com.example.databinding.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.databinding.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String){
    Glide.with(view.context)
        .load(url).centerCrop()
        .placeholder(R.drawable.ic_error)
        .into(view)
    }

@BindingAdapter("imagePicassoUrl")
fun loadPicasso(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}