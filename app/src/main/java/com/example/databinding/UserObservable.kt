package com.example.databinding
import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.databinding.activity.CustomDataBindingActivity

class UserObservable(private val context: Context): BaseObservable() {
    var change = ObservableBoolean()
    var nameChange = ObservableField<String>()


    fun onClickObservable(){
        if(!change.get()){
            nameChange.set("Observable ngon")
        }else{
            nameChange.set(" ")
        }
    }

    fun onClickToCustomActivity(){
        CustomDataBindingActivity.getIntent(context)
    }


}