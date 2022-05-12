package com.example.wanandroid.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.entity.Chapter
import com.example.wanandroid.recycleViewDrag.Helper

class SysSystemAdapter : RecyclerView.Adapter<SysSystemAdapter.SysHolder>(){

    var chapters : List<Chapter> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SysHolder {
        return SysHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SysHolder, position: Int) {
        holder.bindWithChapter(chapters[position],position)
    }

    override fun getItemCount(): Int {
        return chapters.size
    }

    class SysHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        companion object{
            fun newInstance(parent: ViewGroup): SysHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.system_item_layout, parent, false)
                return SysHolder(view)
            }
        }

        fun bindWithChapter(chapter : Chapter,position: Int) {
            with(itemView){
                val title = this.findViewById<TextView>(R.id.Title_sys)
                title.text = chapter.name
                val childrenName = this.findViewById<TextView>(R.id.ChildrenName_sys)
                childrenName.text = StringBuilder().run {
                    for (name in chapter.children)append("${name.name}    ")
                    toString()
                }
                itemView.setOnClickListener {
//                    点击后跳转二级页面
                    with(Bundle()){
                        putInt("pos1",position)
                        it.findNavController().navigate(R.id.action_systemFragment_to_sysSysDetailFragment,this)
                    }
                }
            }
        }

    }

}