package com.example.wanandroid.entity

data class ArticleJson(
    val data: ArticleData,
    val errorCode: Int,
    val errorMsg: String
)

data class TopArticleJson(
    val data: List<ArticleEntity>,
    val errorCode: Int,
    val errorMsg: String
)


data class ArticleData(
    val curPage: Int,
    val datas: List<ArticleEntity>,
    val offset: Int,
    val pageCount: Int,
    val size: Int,
    val total: Int
)


data class ArticleEntity(
    val author: String = "",
    val chapterId: Int,
    val chapterName: String = "",
    val collect : Boolean = false,
    val courseId: Int,
//    val desc: String = "",
    val envelopePic: String = "",
    val fresh : Boolean = false,
    val link: String = "",
    val niceDate: String = "",
    val shareUser : String = "",
    val superChapterName: String = "",
    val title: String = "",
    var isTop : Boolean = false
)

