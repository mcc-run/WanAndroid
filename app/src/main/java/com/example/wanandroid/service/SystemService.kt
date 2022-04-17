package com.example.wanandroid.service

import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.entity.NavigationJson
import com.example.wanandroid.entity.SystemJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SystemService {

    @GET("tree/json")
    fun getTreeJson() : Call<SystemJson>

    @GET("navi/json")
    fun getNavJSON() : Call<NavigationJson>

    @GET("{page}/json?cid={cid}")
    fun getArticleList(@Path("page") page : Int,@Path("cid") cid : Int) : Call<ArticleJson>

}