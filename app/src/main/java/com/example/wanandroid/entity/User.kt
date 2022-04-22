package com.example.wanandroid.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class LoginJson(val errorMsg: String, val errorCode: Int, val data: LoginBean)

@Entity
data class UserMsg(@PrimaryKey val id: Int = -1, val username: String = "", val password: String = "")

data class LoginBean(
    val admin: Boolean = false,
    val coinCount: Int = 0,
    val collectIds: List<Int> = listOf(),
    val email: String = "",
    val icon: String = "",
    val id: Int = -1,
    val nickname: String = "",
    val password: String = "",
    val publicName: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = ""
)