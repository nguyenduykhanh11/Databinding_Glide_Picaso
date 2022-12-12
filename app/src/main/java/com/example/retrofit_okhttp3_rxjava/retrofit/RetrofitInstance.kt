package com.example.retrofit_okhttp3_rxjava.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    val api:GitHubApi by lazy {
        val interceptor = Interceptor { chain ->
            val request: Request = chain.request()
            val builder: Request.Builder = request.newBuilder()
//          builder.addHeader() điền key và value, nếu Api có sử dụng header để bảo mật
            chain.proceed(builder.build())
        }
//        Log ra thông tin call api
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .readTimeout(30, TimeUnit.SECONDS) // set thời thông báo lỗi
            .connectTimeout(30, TimeUnit.SECONDS) // set thời thông báo lỗi
            .retryOnConnectionFailure(false) // thử lại khi kết nối lỗi
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.github.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GitHubApi::class.java)
    }
}