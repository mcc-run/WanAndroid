package com.example.wanandroid.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.entity.NavigationData
import com.example.wanandroid.viewmodel.SystemViewModel

class SysNavTypeAdapter(val model: SystemViewModel) : RecyclerView.Adapter<SysNavTypeAdapter.TypeHolder>() {

    var navigationData : List<NavigationData> = listOf()

    var position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        return TypeHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        holder.bindWithNav(navigationData[position],position == this.position,model,position)
    }

    override fun getItemCount(): Int {
        return navigationData.size
    }


    class TypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        companion object{

            fun newInstance(parent: ViewGroup): TypeHolder {
                val view = Button(parent.context)
                return TypeHolder(view)
            }
        }

        fun bindWithNav(nav: NavigationData, isChoice: Boolean, model: SystemViewModel,position: Int){
            with(itemView as Button){
                text = nav.name
                setOnClickListener {
                    model.isScroll.postValue(false)
                    model.firstVis.postValue(position)
                    model.position.postValue(position)
                }
                width = 150
                backgroundTintList = if (!isChoice){
                     ColorStateList.valueOf(Color.rgb(238, 238, 238))
                }else ColorStateList.valueOf(Color.rgb(180, 160, 255))
            }
        }

    }
}