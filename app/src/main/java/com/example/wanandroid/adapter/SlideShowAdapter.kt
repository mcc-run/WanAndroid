package com.example.wanandroid.adapter

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.wanandroid.R
import com.example.wanandroid.entity.BannerEntity
import com.example.wanandroid.ui.fragment.SlideShowPhotoFragment
import java.lang.System.load

class SlideShowAdapter() : RecyclerView.Adapter<SlideShowAdapter.ViewHolder>(){

    var data = listOf<BannerEntity>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.photo_SlideShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_slide_show_photo,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(data[position].imagePath)
            .placeholder(R.drawable.loading)
            .into(holder.imageView)
        val bundle = Bundle()
        bundle.putString("url",data[position].url)
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_homeListFragment_to_articleFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}