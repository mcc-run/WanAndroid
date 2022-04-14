package com.example.wanandroid.http

import com.example.wanandroid.entity.BannerJson
import com.example.wanandroid.service.SlideShowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitUtil {

    private const val BASE_URL = "https://www.wanandroid.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service : Class<T>) : T = retrofit.create(service)

}