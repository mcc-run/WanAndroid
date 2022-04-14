package com.example.wanandroid.entity

data class BannerJson(val data : List<BannerEntity>,val errorCode : Int,val errorMsg : String)

data class BannerEntity(val id: Int, val imagePath: String, val title: String, val url: String)