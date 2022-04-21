package com.example.wanandroid.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.entity.ArticleEntity

class SysSysItemAdapter(val actionId : Int) : PagedListAdapter<ArticleEntity, RecyclerView.ViewHolder>(HomeListAdapter.DiffCallback) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Holder).bindWithArticle(getItem(position)!!,actionId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Holder.newInstance(parent)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun newInstance(parent: ViewGroup): Holder {
                val inflater = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sys_article_item_layout, parent, false)

                return Holder(inflater)
            }
        }

        fun bindWithArticle(articleEntity: ArticleEntity,actionId : Int) {
            with(itemView) {
                val author = findViewById<TextView>(R.id.author)
                val date = findViewById<TextView>(R.id.date)
                val title = findViewById<TextView>(R.id.title)
                val chapterName = findViewById<TextView>(R.id.chapterName)
                val thumb = findViewById<ImageView>(R.id.thumb)
                articleEntity.let{
                    author.text = if (it.author == "")it.shareUser else it.author
                    date.text = it.niceDate
                    title.text = it.title
                    chapterName.text = it.chapterName
                    thumb.setImageResource(if (it.collect)R.drawable.ic_thumb_fill else R.drawable.ic_thumb)
                    val bundle = Bundle()
                    bundle.putString("url",articleEntity.link)
                    this.setOnClickListener {
                        this.findNavController().navigate(actionId,bundle)
                    }
                }

            }
        }

    }


}