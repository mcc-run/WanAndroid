package com.example.wanandroid.Factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import com.example.wanandroid.datasource.Articledatasource
import com.example.wanandroid.datasource.SysArticledatasource
import com.example.wanandroid.entity.ArticleEntity


class SysArticleDataSourceFactory(val cid : Int) : Factory<Int,ArticleEntity>() {
    private val _articledatasource = MutableLiveData<SysArticledatasource>()
    val articledatasource : LiveData<SysArticledatasource> = _articledatasource
    override fun create(): DataSource<Int, ArticleEntity> {
        return SysArticledatasource(cid).also { _articledatasource.postValue(it)}
    }
}