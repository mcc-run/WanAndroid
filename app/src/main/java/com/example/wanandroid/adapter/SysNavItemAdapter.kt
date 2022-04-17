package com.example.wanandroid.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.entity.Article
import com.example.wanandroid.entity.NavigationData
import com.example.wanandroid.viewmodel.SystemViewModel


class SysNavItemAdapter() : RecyclerView.Adapter<SysNavItemAdapter.DetailHolder>() {

    var data: List<NavigationData> = listOf()

    val colors = listOf(
        Color.rgb(210, 146, 0),
        Color.rgb(255, 185, 0),
        Color.rgb(216, 59, 1),
        Color.rgb(255, 140, 0),
        Color.rgb(164, 38, 44),
        Color.rgb(180, 0, 158),
        Color.rgb(180, 160, 255),
        Color.rgb(227, 0, 140),
    )


    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.sys_nav_item_layout, parent, false)
        context = parent.context
        return DetailHolder(inflater)
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.text.text = data[position].name
        setDetail(holder.detail, context, data = data[position].articles,holder.itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class DetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.findViewById<TextView>(R.id.text_chapterName)
        var detail = itemView.findViewById<LinearLayout>(R.id.detail)
    }

    //    绘制自动换行的线性布局
    private fun setDetail(parent: LinearLayout, context: Context, data: List<Article>,view: View) {
        parent.removeAllViews()
        var row = LinearLayout(context)
        row.orientation = LinearLayout.HORIZONTAL
//        行布局参数
        val rowParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rowParams.topMargin = 10
        row.layoutParams = rowParams
//        列布局参数
        val buttonParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        buttonParams.leftMargin = 15

        val maxWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            300f,
            context.getResources().getDisplayMetrics()
        )
            .toInt()
        var spaceWidth = maxWidth

        for (article in data) {
            val button = Button(context)
            button.text = article.title
            button.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("url",article.link)
                view.findNavController().navigate(R.id.action_systemFragment_to_articleFragment2,bundle)
            }
            button.layoutParams = buttonParams
            button.measure(0, 0)
            button.backgroundTintList = ColorStateList.valueOf(Color.rgb(238, 238, 238))
            button.setTextColor(colors.random())
            if (button.measuredWidth + 15 >= spaceWidth) {
                parent.addView(row)
                row = LinearLayout(context)
                row.orientation = LinearLayout.HORIZONTAL
                row.layoutParams = rowParams
                row.addView(button)
                spaceWidth = maxWidth - button.measuredWidth
            } else {
                row.addView(button)
                spaceWidth -= (button.measuredWidth + 15)
            }
        }
        parent.addView(row)

    }


}