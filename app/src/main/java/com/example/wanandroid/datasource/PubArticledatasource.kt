package com.example.wanandroid.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.wanandroid.entity.ArticleData
import com.example.wanandroid.entity.ArticleEntity
import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.entity.TopArticleJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.HomeService
import com.example.wanandroid.service.PublicService
import com.example.wanandroid.service.SystemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PubArticledatasource(val cid : Int) : PageKeyedDataSource<Int, ArticleEntity>() {


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleEntity>) {

        val retrofit = retrofitUtil.create(PublicService::class.java)
        retrofit.getArticleById(cid,params.key).enqueue(object : Callback<ArticleJson> {
            override fun onResponse(call: Call<ArticleJson>, response: Response<ArticleJson>) {
                val data = response.body()
                callback.onResult(data!!.data.datas!!,params.key+1)
            }
            override fun onFailure(call: Call<ArticleJson>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleEntity>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleEntity>
    ) {
        val retrofit = retrofitUtil.create(PublicService::class.java)
        retrofit.getArticleById(cid,1).enqueue(object : Callback<ArticleJson>{
            override fun onResponse(call: Call<ArticleJson>, response: Response<ArticleJson>) {
                val data = response.body()
                callback.onResult(data!!.data.datas!!,null,2)
            }
            override fun onFailure(call: Call<ArticleJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}