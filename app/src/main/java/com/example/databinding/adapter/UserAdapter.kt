package com.example.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.model.User
import com.example.databinding.databinding.ItemDataBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    var listuser = emptyList<User>()
    fun setData(items: List<User>) {
        this.listuser = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = listuser.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.binding.user = listuser[position]
    }
    class UserHolder(var binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}