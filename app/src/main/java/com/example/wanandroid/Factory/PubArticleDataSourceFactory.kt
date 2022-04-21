package com.example.wanandroid.Factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import com.example.wanandroid.datasource.PubArticledatasource
import com.example.wanandroid.entity.ArticleEntity


class PubArticleDataSourceFactory(val cid : Int) : Factory<Int,ArticleEntity>() {
    private val _articledatasource = MutableLiveData<PubArticledatasource>()
    val articledatasource : LiveData<PubArticledatasource> = _articledatasource
    override fun create(): DataSource<Int, ArticleEntity> {
        return PubArticledatasource(cid).also { _articledatasource.postValue(it)}
    }
}