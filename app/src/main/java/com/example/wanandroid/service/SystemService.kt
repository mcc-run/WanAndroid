package com.example.wanandroid.service

import com.example.wanandroid.entity.SystemJson
import retrofit2.Call
import retrofit2.http.GET

interface SystemService {

    @GET("tree/json")
    fun getTreeJson() : Call<SystemJson>

}