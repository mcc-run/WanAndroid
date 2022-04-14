package com.example.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.wanandroid.Factory.ArticleDataSourceFactory
import com.example.wanandroid.adapter.SlideShowAdapter
import com.example.wanandroid.entity.BannerEntity
import com.example.wanandroid.entity.BannerJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.SlideShowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeListViewModel : ViewModel() {

//    用于实现轮播图效果
    var viewPager2 : ViewPager2? = null
    var currentItem = MutableLiveData<Int>(0)
    fun rotateBanner(){
        var temp = currentItem.value!!
        temp = (temp + 1) % Int.MAX_VALUE
        currentItem.value = temp

        viewPager2?.let {
            val adapter = it.adapter as SlideShowAdapter
            if (adapter.data.isNotEmpty()){
                val size = adapter.data.size
                it.setCurrentItem(temp % size,true)
            }
        }
    }

//    用于获取广告条
    fun getBanners(adapter: SlideShowAdapter){
        val slideShowService = retrofitUtil.create(SlideShowService::class.java)
        slideShowService.getBanners().enqueue(object : Callback<BannerJson>{
            override fun onResponse(call: Call<BannerJson>, response: Response<BannerJson>) {
                val data = response.body()
                adapter.data = data!!.data
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<BannerJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }


//    用于获取文章列表
    val articleDataSourceFactory = ArticleDataSourceFactory()
    val articleList = articleDataSourceFactory.toLiveData(1)

//    处理网络状态
    val networkStatus = Transformations.switchMap(articleDataSourceFactory.articledatasource) {it.networkStatus}
    fun resetQuery() {
        articleList.value?.dataSource?.invalidate()
    }
    fun retry() {
        articleDataSourceFactory.articledatasource.value?.retry?.invoke()
    }


}