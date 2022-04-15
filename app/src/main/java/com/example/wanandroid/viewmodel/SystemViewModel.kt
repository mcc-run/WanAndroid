package com.example.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.adapter.SysSystemAdapter
import com.example.wanandroid.entity.SystemJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.SystemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SystemViewModel : ViewModel() {

    val pages = arrayOf("体系","导航")

    fun getSystemData(adapter : SysSystemAdapter){
        val retrofit = retrofitUtil.create(SystemService::class.java)
        retrofit.getTreeJson().enqueue(object : Callback<SystemJson>{
            override fun onResponse(call: Call<SystemJson>, response: Response<SystemJson>) {
                val data = response.body()!!
                adapter.chapters = data.data!!
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SystemJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }


}