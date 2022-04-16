package com.example.wanandroid.entity

data class NavigationJson(
    val data : List<NavigationData>,
    val errorCode : Int,
    val errorMsg : String
)

data class NavigationData(
    val articles : List<Article>,
    val name : String = ""
)

data class Article(
    val link : String = "https://www.baidu.com",
    val title :String = ""
)