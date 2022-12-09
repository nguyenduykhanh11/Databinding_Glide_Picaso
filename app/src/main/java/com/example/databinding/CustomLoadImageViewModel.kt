package com.example.databinding
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

class CustomLoadImageViewModel:Observable{
    val image1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
    val image2 = "https://baodanang.vn/dataimages/202209/original/images1665561_images1665380_1.jpg"
    val image3 = "https://www.w3schools.com/css/img_forest.jpg"
    var linkUrlGlide = ObservableField<String>()
    var linkUrlPicasso = ObservableField<String>()

    init {
        linkUrlGlide.set(image1)
        linkUrlPicasso.set(image2)
    }
    fun setImageGlide(url: String){
        linkUrlGlide.set(url)
    }
    fun setImagePicasso(url: String){
        linkUrlPicasso.set(url)
    }

    fun onClickGlide(){
        if (linkUrlGlide.get() == image2){
            linkUrlGlide.set(image1)
            return
        }
        linkUrlGlide.set(image2)
    }
    fun onClickPicasso(){
        if (linkUrlPicasso.get() == image3){
            linkUrlPicasso.set(image2)
            return
        }
        linkUrlPicasso.set(image3)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}