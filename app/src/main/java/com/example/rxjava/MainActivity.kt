package com.example.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val TAG = "this"
        val observable = Observable.just(1, 2, 3, 4, 5, 6)
        val observableSecond = Observable.just(7, 8, 9, 10, 11, 12)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAllListenerClick()
    }

    private fun setUpAllListenerClick() {
        binding.btnMap.setOnClickListener {
            unShowRerult()
            setUpMap()
        }
        binding.btnFlatMap.setOnClickListener {
            unShowRerult()
            setUpFlatMap()
        }
        binding.btnConcatMap.setOnClickListener {
            unShowRerult()
            setUpConCatMap()
        }
        binding.btnZip.setOnClickListener {
            unShowRerult()
            setUpZip()
        }
        binding.btnMerge.setOnClickListener {
            unShowRerult()
            setUpMerge(1)
        }
        binding.floatBottom.setOnClickListener {
            unShowRerult()
            setUpMerge(2)
        }
    }

    private fun setUpMerge(compare: Int) {
        Observable.merge(observable, observableSecond)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { item -> item % compare == 0}
            .toList()
            .subscribe(
                { list ->
                    showResult(list.toString())
                    Log.d(TAG, list.toString())
                },
                {
                    Log.d(TAG, it.toString())
                })
    }


    private fun setUpZip() {
        Observable.zip(observable, observableSecond) { list1, list2 -> "$list1 - $list2" }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toList()
            .subscribe { list ->
                showResult(list.toString())
                Log.d(TAG, list.toString())
            }
    }

    //    Nó giống như FlatMap nhưng nó xữ lý theo đồng bộ
    private fun setUpConCatMap() {
        observable
            .map { item -> item }
            .concatMap { item ->
                val delay = (0..5).random() //random thời gian delay từ 0 -> 10
                return@concatMap Observable.just(item).delay(delay.toLong(), TimeUnit.SECONDS)
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { item ->
                    showResult(item.toString())
                    Log.d(TAG, item.toString())
                },
                {
                    Log.d(TAG, it.toString())
                }
            )
    }

    // map chạy trả về từng Object
    private fun setUpMap() {
        var i = 1
        observable
            .map { item -> item.toString() + "x${i++}" }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toList()
            .subscribe(
                { item ->
                    showResult(item.toString())
                    Log.d(TAG, item.toString())
                },
                {
                    Log.d(TAG, it.toString())
                }
            )
    }

    //    flatMap chạy xữ lý và trả về Observable, nó xử lý bất đồng bộ
    private fun setUpFlatMap() {
        observable
            .map { item -> item }
            .flatMap { item ->
                val delay = (0..5).random() //random thời gian delay từ 0 -> 10
                return@flatMap Observable.just(item).delay(delay.toLong(), TimeUnit.SECONDS)
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { item ->
                    showResult(item.toString())
                    Log.d(TAG, item.toString())
                },
                {
                    Log.d(TAG, it.toString())
                }
            )
    }

    private fun unShowRerult() {
        with(binding) {
            tvResult.text = null
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showResult(result: String) {
        with(binding) {
            tvResult.text = result
            progressBar.visibility = View.GONE
        }
    }

}