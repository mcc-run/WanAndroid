package com.example.wanandroid

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.wanandroid.constan.UserMessage
import com.example.wanandroid.database.WanDataBase
import com.example.wanandroid.databinding.ActivityMainBinding
import com.example.wanandroid.entity.LoginJson
import com.example.wanandroid.http.retrofitUtil
import com.example.wanandroid.service.MyService
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUser()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView
        navView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_system,
                R.id.navigation_public,
                R.id.navigation_my
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun initUser() {
        val database = Room.databaseBuilder(this, WanDataBase::class.java, "WanAndroid").build()
        val dao = database.getUserDao()
        val job = Job()
        val scope = CoroutineScope(job)
        scope.launch {
            val data = dao.getAllUserMsg()
            if (data.isNotEmpty()) {
                UserMessage.userMsg = data[0]
            }
            if (UserMessage.userMsg.id == -1)return@launch
            val retrofit = retrofitUtil.create(MyService::class.java)
            retrofit.login(UserMessage.userMsg.username, UserMessage.userMsg.password)
                .enqueue(object : Callback<LoginJson> {
                    override fun onResponse(call: Call<LoginJson>, response: Response<LoginJson>) {
                        val data = response.body()
                        if(data.toString().contains("账号密码不匹配！")){
                            UserMessage.userMsg = com.example.wanandroid.entity.UserMsg()
                            dao.deleteAllUserMsg()
                        }else{
                            UserMessage.userBean = data!!.data
                        }
                    }
                    override fun onFailure(call: Call<LoginJson>, t: Throwable) {
                    }

                })

        }

    }
}