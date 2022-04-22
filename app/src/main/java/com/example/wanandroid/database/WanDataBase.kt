package com.example.wanandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wanandroid.dao.UserDao
import com.example.wanandroid.entity.UserMsg

@Database(entities = [UserMsg::class], version = 1)
abstract class WanDataBase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

}