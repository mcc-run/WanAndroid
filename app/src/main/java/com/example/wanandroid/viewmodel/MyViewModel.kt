package com.example.wanandroid.viewmodel

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.wanandroid.constan.UserMessage
import com.example.wanandroid.database.WanDataBase
import com.example.wanandroid.entity.LoginBean
import com.example.wanandroid.entity.UserMsg
import com.example.wanandroid.entity.LoginJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.MyService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    var isLogin = MutableLiveData(false)



    fun login(username: String, password: String, context: Context){
        val retrofit = retrofitUtil.create(MyService::class.java)
        retrofit.login(username, password).enqueue(object : Callback<LoginJson> {
            override fun onResponse(call: Call<LoginJson>, response: Response<LoginJson>) {
                val data = response.body()
                println(data)
                val builder = AlertDialog.Builder(context)
                if (data.toString().contains("data=null")) {
                    builder.setMessage("账号密码不匹配！")
                        .setPositiveButton("确定") { _, _ ->}
                    // Create the AlertDialog object and return it
                } else {
                    builder.setMessage("登录成功！").setPositiveButton("确定") {
                        _,_ -> isLogin.postValue(true)
                    }
                    val database =
                        Room.databaseBuilder(context, WanDataBase::class.java, "WanAndroid").build()
                    val dao = database.getUserDao()
                    val job = Job()
                    val scope = CoroutineScope(job)
                    scope.launch {
                        dao.deleteAllUserMsg()
                        val msg = data!!.data.run {
                            UserMsg(this.id,this.username,password)
                        }
                        dao.insertUserMsg(msg)
                        UserMessage.userMsg = msg
                        UserMessage.userBean = data.data
                    }
                    //    取消协程
                    job.cancel()
                }
                builder.create()
                builder.show()

            }

            override fun onFailure(
                call: Call<LoginJson>, t: Throwable
            ) {
                t.printStackTrace()
            }
        })
    }

    fun logOut(context: Context) {
        UserMessage.userBean = LoginBean()
        UserMessage.userMsg = UserMsg()
        val database =
            Room.databaseBuilder(context, WanDataBase::class.java, "WanAndroid").build()
        val dao = database.getUserDao()
        val job = Job()
        val scope = CoroutineScope(job)
        scope.launch {
            dao.deleteAllUserMsg()
        }
        job.cancel()
        isLogin.postValue(false)
    }

    var isRegister = MutableLiveData(false)

    fun register(text: String, text1: String, text2: String, context: Context) {
        val retrofit = retrofitUtil.create(MyService::class.java)
        retrofit.register(text,text1,text2).enqueue(object : Callback<LoginJson>{
            override fun onResponse(call: Call<LoginJson>, response: Response<LoginJson>) {
                val data = response.body()
                val builder = AlertDialog.Builder(context)
                if (data.toString().contains("data=null")){
                    builder.setMessage(data!!.errorMsg)
                }else {
                    builder.setMessage("注册成功")
                    isRegister.postValue(true)
                }
                builder.setPositiveButton("确定",){ _,_ -> }
                builder.create()
                builder.show()
            }

            override fun onFailure(call: Call<LoginJson>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}