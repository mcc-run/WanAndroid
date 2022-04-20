package com.example.wanandroid.service

import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.entity.NavigationJson
import com.example.wanandroid.entity.SystemJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SystemService {

    @GET("tree/json")
    fun getTreeJson() : Call<SystemJson>

    @GET("navi/json")
    fun getNavJSON() : Call<NavigationJson>

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page : Int,@Query("cid")cid : Int) : Call<ArticleJson>

}