package com.example.wanandroid.service

import com.example.wanandroid.entity.ArticleData
import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.entity.PublicJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicService {

    @GET("wxarticle/chapters/json")
    fun getWxArticle() : Call<PublicJson>

    @GET("wxarticle/list/{id}/{page}/json")
    fun getArticleById(@Path("id")id : Int,@Path("page")page : Int) : Call<ArticleJson>


}