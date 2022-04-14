package com.example.wanandroid.service

import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.entity.TopArticleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page : Int) : Call<ArticleJson>

    @GET("article/top/json")
    fun getTopArticleList() : Call<TopArticleJson>

}