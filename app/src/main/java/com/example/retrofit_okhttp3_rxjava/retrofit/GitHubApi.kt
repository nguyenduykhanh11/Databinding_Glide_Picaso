package com.example.retrofit_okhttp3_rxjava.retrofit

import com.example.retrofit_okhttp3_rxjava.model.GitHubEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface GitHubApi {
    @GET("list")
    fun getData(): Observable<GitHubEntity>
}