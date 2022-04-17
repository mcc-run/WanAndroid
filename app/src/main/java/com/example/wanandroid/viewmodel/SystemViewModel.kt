package com.example.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.adapter.SysNavItemAdapter
import com.example.wanandroid.adapter.SysNavTypeAdapter
import com.example.wanandroid.adapter.SysSystemAdapter
import com.example.wanandroid.entity.Chapter
import com.example.wanandroid.entity.NavigationJson
import com.example.wanandroid.entity.SystemJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.SystemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SystemViewModel : ViewModel() {

    val pages = arrayOf("体系","导航")

    var chapters = MutableLiveData<List<Chapter>?>()


    fun getSystemData(adapter : SysSystemAdapter){
        val retrofit = retrofitUtil.create(SystemService::class.java)
        retrofit.getTreeJson().enqueue(object : Callback<SystemJson>{
            override fun onResponse(call: Call<SystemJson>, response: Response<SystemJson>) {
                val data = response.body()!!
                adapter.chapters = data.data!!
                chapters.value = null
                chapters.postValue(data.data!!)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SystemJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun getNavigationData(
        NavAdapter: SysNavTypeAdapter,
        ItemAdapter: SysNavItemAdapter
    ) {
        val retrofit = retrofitUtil.create(SystemService::class.java)
        retrofit.getNavJSON().enqueue(object : Callback<NavigationJson>{
            override fun onResponse(
                call: Call<NavigationJson>,
                response: Response<NavigationJson>
            ) {
                val data = response.body()
                NavAdapter.navigationData = data!!.data
                ItemAdapter.data = data!!.data
                NavAdapter.notifyDataSetChanged()
                ItemAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<NavigationJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    var firstVis = MutableLiveData(0)
    var isScroll = MutableLiveData(false)
    var position = MutableLiveData(0)


}