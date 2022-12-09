package com.example.databinding.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.*
import com.example.databinding.UserObservable
import com.example.databinding.UserViewModel
import com.example.databinding.adapter.UserAdapter
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.User

class MainActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by lazy { ViewModelProvider(this)[UserViewModel::class.java] }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setUpUser()
        setUpObsevable()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        binding.userViewModel = viewModel
        val adapter = UserAdapter()
        viewModel.listUser.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            adapter.listuser = it
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        })
    }

    private fun setUpUser() {
        val presenter = Presenter(this)
        binding.user = User("Nguyễn Duy Khánh", "quảng trị")
        binding.presenter = presenter
    }

    private fun setUpObsevable() {
        val mUserObservable = UserObservable(this)
        binding.userObservable = mUserObservable
    }
}