package com.example.wanandroid.dao

import androidx.room.*
import com.example.wanandroid.entity.UserMsg

@Dao
interface UserDao {

    @Insert
    fun insertUserMsg(userMsg:UserMsg)


    @Query("delete from UserMsg")
    fun deleteAllUserMsg()

    @Query("select * from UserMsg")
    fun getAllUserMsg() : List<UserMsg>;
    
}