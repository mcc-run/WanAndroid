package com.example.wanandroid.entity


data class SystemJson(
    val data : List<Chapter>,
    val errorCode : Int = 0,
    val error : String = "",
)

data class Chapter(
    val children : List<Children>,
    val courseId : Int = 0,
    val id : Int = 0,
    val name : String = "",
    val order : Int = 0,
    val parentChapterId: Int = 0
)

data class Children(
    val id : Int = 0,
    val name : String = "",
)



