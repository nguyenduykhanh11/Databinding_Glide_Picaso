package com.example.retrofit_okhttp3_rxjava

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.retrofit_okhttp3_rxjava.databinding.ActivityMainBinding
import com.example.retrofit_okhttp3_rxjava.retrofit.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpListenerGetDataGitHub()
    }

    private fun stopProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun setUpListenerGetDataGitHub() {
        binding.btnGetData.setOnClickListener {
            showProgressBar()
            RetrofitInstance.api.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { git ->
                        Log.d("this", git.toString())
                        Toast.makeText(applicationContext, git.toString(), Toast.LENGTH_SHORT).show()
                        stopProgressBar()
                    },
                    { throwable->
                        Log.e("this", throwable.message ?: "onError")
                        stopProgressBar()
                    }
                )
        }
    }
}