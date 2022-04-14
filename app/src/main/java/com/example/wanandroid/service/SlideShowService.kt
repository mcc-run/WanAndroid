package com.example.wanandroid.service

import com.example.wanandroid.entity.BannerEntity
import com.example.wanandroid.entity.BannerJson
import retrofit2.Call
import retrofit2.http.GET

interface SlideShowService {

    @GET("banner/json")
    fun getBanners() : Call<BannerJson>

}