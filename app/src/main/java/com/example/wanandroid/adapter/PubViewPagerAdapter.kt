package com.example.wanandroid.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.Factory.PubArticleDataSourceFactory
import com.example.wanandroid.R
import com.example.wanandroid.entity.data
import com.example.wanandroid.recycleViewDrag.Helper

class PubViewPagerAdapter(
    val author: MutableLiveData<List<data>?>,
    val lifecycleOwner: LifecycleOwner,
    val context: Context) : RecyclerView.Adapter<PubViewPagerAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindWithId(author.value!!.get(position).id,lifecycleOwner,context)
    }

    override fun getItemCount(): Int {
        return author.value?.size ?: 0
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        companion object{
            fun newInstance(parent: ViewGroup): Holder {
                val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycleview,parent,false)
                return Holder(inflater)
            }
        }
        fun bindWithId(id: Int, lifecycleOwner: LifecycleOwner, context: Context) {
            val adapter = SysSysItemAdapter(R.id.action_publicFragment_to_articleFragment3)
            val recycleView = itemView.findViewById<RecyclerView>(R.id.RecycleView)
            val helper = Helper.getHelper(enableDrag = true, enableSwipe = true)
            helper.attachToRecyclerView(recycleView)
            recycleView.adapter = adapter
            recycleView.layoutManager = LinearLayoutManager(context)
//    用于获取文章列表
            val articleDataSourceFactory = PubArticleDataSourceFactory(id)
            val articleList = articleDataSourceFactory.toLiveData(1)

            articleList.observe(lifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}