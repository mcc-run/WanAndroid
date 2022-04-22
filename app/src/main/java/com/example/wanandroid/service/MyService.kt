package com.example.wanandroid.service

import com.example.wanandroid.entity.UserMsg
import com.example.wanandroid.entity.LoginJson
import retrofit2.Call
import retrofit2.http.*

interface MyService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginJson>

    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Call<LoginJson>

}