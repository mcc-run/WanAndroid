package com.example.wanandroid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.recycleViewDrag.Helper
import com.example.wanandroid.viewmodel.SystemViewModel


class SystemAdapter(val systemViewModel: SystemViewModel, val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) R.layout.recycleview else R.layout.sys_navigation_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.recycleview -> SystemHolder.newInstance(parent)
            else -> NavHolder.newInstance(parent, systemViewModel,lifecycleOwner)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.recycleview -> (holder as SystemHolder).bindWithModel(systemViewModel)
            else -> { (holder as NavHolder).bindWithModel(systemViewModel) }
        }
    }

    override fun getItemCount(): Int {
        return systemViewModel.pages.size
    }

    class SystemHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun newInstance(parent: ViewGroup): SystemHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycleview, parent, false)
                val recyclerView = view.findViewById<RecyclerView>(R.id.RecycleView)
                val adapter = SysSystemAdapter()
                recyclerView.layoutManager = LinearLayoutManager(parent.context)
                recyclerView.adapter = adapter
                return SystemHolder(view)
            }
        }

        fun bindWithModel(model: SystemViewModel) {
            val recyclerView = itemView.findViewById<RecyclerView>(R.id.RecycleView)
            val helper = Helper.getHelper(enableDrag = true, enableSwipe = true)
            helper.attachToRecyclerView(recyclerView)
            model.getSystemData(recyclerView.adapter as SysSystemAdapter)
        }

    }


    class NavHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {

            @SuppressLint("ClickableViewAccessibility")
            fun newInstance(
                parent: ViewGroup,
                model: SystemViewModel,
                lifecycleOwner: LifecycleOwner
            ): NavHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sys_navigation_layout, parent, false)
                with(view) {
                    val reNav = findViewById<RecyclerView>(R.id.re_type_nav)
                    val NavTypeAdapter = SysNavTypeAdapter(model)
                    reNav.layoutManager = LinearLayoutManager(parent.context)
                    reNav.adapter = NavTypeAdapter
                    val reItem = findViewById<RecyclerView>(R.id.re_detail_nav)
                    val NavItemAdapter = SysNavItemAdapter()
                    reItem.layoutManager = LinearLayoutManager(parent.context)
                    reItem.adapter = NavItemAdapter
                    //                    ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    var last_pos = 0
                    //??????RecycleView???????????????
                    reItem.setOnTouchListener(OnTouchListener { v, event -> //????????????recyclerView????????????isRecyclerScroll ???true
                            model.isScroll.postValue(true)
                        false
                    })
                    //??????RecycleView???????????????
                    reNav.setOnTouchListener(OnTouchListener { v, event -> //????????????recyclerView????????????isRecyclerScroll ???true
                            model.isScroll.postValue(false)
                        false
                    })

                    reItem.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        //???????????????????????????
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (!(model.isScroll.value!!))return
                            // ?????????????????????
                            val firstItemPosition =
                                recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0))
                            if (last_pos != firstItemPosition) {
                                model.position.postValue(firstItemPosition)
                                reNav.scrollToPosition(firstItemPosition)
                                last_pos = firstItemPosition
                            }
                        }
                    })
                    model.firstVis.observe(lifecycleOwner) {
                        reItem.smoothScrollToPosition(it)
                    }
                    model.position.observe(lifecycleOwner) {
                        NavTypeAdapter.position = it
                        NavTypeAdapter.notifyDataSetChanged()
                    }
                }
                return NavHolder(view)
            }
        }

        fun bindWithModel(model: SystemViewModel) {
            val reNav = itemView.findViewById<RecyclerView>(R.id.re_type_nav)
            val reItem = itemView.findViewById<RecyclerView>(R.id.re_detail_nav)
            model.getNavigationData(
                reNav.adapter as SysNavTypeAdapter,
                reItem.adapter as SysNavItemAdapter
            )
        }

    }


}