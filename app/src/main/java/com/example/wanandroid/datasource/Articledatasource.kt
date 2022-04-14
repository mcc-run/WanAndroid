package com.example.wanandroid.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.wanandroid.entity.ArticleEntity
import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.entity.TopArticleJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
enum class NetworkStatus {
    INITIAL_LOADING,
    LOADING,
    LOADED,
    FAILED,
    COMPLETED
}

class Articledatasource : PageKeyedDataSource<Int, ArticleEntity>() {
    var retry : (()->Any)? = null
    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus : LiveData<NetworkStatus> = _networkStatus
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleEntity>) {
        retry = null
        _networkStatus.postValue(NetworkStatus.LOADING)
        val retrofit = retrofitUtil.create(HomeService::class.java)
        retrofit.getArticleList(params.key).enqueue(object : Callback<ArticleJson> {
            override fun onResponse(call: Call<ArticleJson>, response: Response<ArticleJson>) {
                val data = response.body()
                callback.onResult(data = data!!.data.datas!!, params.key+1)
                _networkStatus.postValue(NetworkStatus.LOADED)
            }
            override fun onFailure(call: Call<ArticleJson>, t: Throwable) {
                retry = {loadAfter(params,callback)}
                _networkStatus.postValue(NetworkStatus.FAILED)
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
        retry = null
        _networkStatus.postValue(NetworkStatus.INITIAL_LOADING)
        val retrofit = retrofitUtil.create(HomeService::class.java)
        retrofit.getTopArticleList().enqueue(object : Callback<TopArticleJson> {
            override fun onResponse(call: Call<TopArticleJson>, response: Response<TopArticleJson>) {
                val data = response.body()
                for (data in data?.data!!)data.isTop = true
                callback.onResult(data = data!!.data, null, 0)
                _networkStatus.postValue(NetworkStatus.LOADED)
            }
            override fun onFailure(call: Call<TopArticleJson>, t: Throwable) {
                retry = {loadInitial(params,callback)}
                _networkStatus.postValue(NetworkStatus.FAILED)
                t.printStackTrace()
            }
        })
    }

}