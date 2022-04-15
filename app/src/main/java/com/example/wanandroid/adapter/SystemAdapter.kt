package com.example.wanandroid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.viewmodel.SystemViewModel

class SystemAdapter(val systemViewModel: SystemViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) R.layout.sys_system_layout else R.layout.sys_navigation_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.sys_system_layout -> SysstemHolder.newInstance(parent)
            else -> NavHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            R.layout.sys_system_layout -> (holder as SysstemHolder).bindWithModel(systemViewModel)
            else -> {}
        }
    }

    override fun getItemCount(): Int {
        return systemViewModel.pages.size
    }

    class SysstemHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
        companion object{
            fun newInstance(parent: ViewGroup): SysstemHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sys_system_layout, parent, false)
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycleView_sys)
                val adapter = SysSystemAdapter()
                recyclerView.layoutManager = LinearLayoutManager(parent.context)
                recyclerView.adapter = adapter
                return SysstemHolder(view)
            }
        }

        fun bindWithModel(model : SystemViewModel){
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycleView_sys)
            model.getSystemData(recyclerView.adapter as SysSystemAdapter)
        }

    }

    class NavHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        companion object{
            fun newInstance(parent: ViewGroup): NavHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sys_navigation_layout, parent, false)
                return NavHolder(view)
            }
        }
    }


}