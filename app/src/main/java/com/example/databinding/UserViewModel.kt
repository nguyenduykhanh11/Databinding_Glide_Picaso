package com.example.databinding

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.databinding.model.User

class UserViewModel: ViewModel(), Observable{
    val isStringEmpty = MutableLiveData<Boolean>()
    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputAddress = MutableLiveData<String>()

    val listUser = MutableLiveData<ArrayList<User>>()
    private val arraylstUser = ArrayList<User>()
    init {
        isStringEmpty.value = false
    }
    fun addData() {
        if(inputName.value!!.isBlank()|| inputAddress.value!!.isBlank()){
            isStringEmpty.value = true
        }else{
            val user = User(inputName.value!!, inputAddress.value!!)
            arraylstUser.add(user)
            listUser.value = arraylstUser
            inputName.value = " "
            inputAddress.value = " "
        }
    }

    fun clearData(){
        arraylstUser.clear()
        listUser.value = arraylstUser
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}
