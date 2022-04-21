package com.example.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.adapter.PubViewPagerAdapter
import com.example.wanandroid.entity.PublicJson
import com.example.wanandroid.entity.data
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.PublicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PublicViewModel : ViewModel() {

    var author = MutableLiveData<List<data>?>()

    fun getWxArticle(adapter: PubViewPagerAdapter) {
        val retrofit = retrofitUtil.create(PublicService::class.java)
        retrofit.getWxArticle().enqueue(object : Callback<PublicJson>{
            override fun onResponse(call: Call<PublicJson>, response: Response<PublicJson>) {
                val data = response.body()
//                给设配器添加数据
                data?.let {
                    author.value = null
                    author.postValue(it.data)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<PublicJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}