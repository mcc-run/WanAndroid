package com.example.wanandroid

import com.example.wanandroid.entity.ArticleJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.HomeService
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getArticleList(){
        val retrofit = retrofitUtil.create(HomeService::class.java)
        retrofit.getArticleList(1).enqueue(object : Callback<ArticleJson> {
            override fun onResponse(call: Call<ArticleJson>, response: Response<ArticleJson>) {
                val data = response.body()
                for (data in data?.data?.datas!!){
                    println(data)
                }
            }

            override fun onFailure(call: Call<ArticleJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}