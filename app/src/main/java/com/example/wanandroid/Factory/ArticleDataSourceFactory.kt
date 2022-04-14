package com.example.wanandroid.Factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import com.example.wanandroid.datasource.Articledatasource
import com.example.wanandroid.entity.ArticleEntity


class ArticleDataSourceFactory : Factory<Int,ArticleEntity>() {
    private val _articledatasource = MutableLiveData<Articledatasource>()
    val articledatasource : LiveData<Articledatasource> = _articledatasource
    override fun create(): DataSource<Int, ArticleEntity> {
        return Articledatasource().also { _articledatasource.postValue(it)}
    }
}