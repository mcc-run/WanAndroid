package com.example.wanandroid.adapter

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.wanandroid.R
import com.example.wanandroid.datasource.NetworkStatus
import com.example.wanandroid.entity.ArticleEntity
import com.example.wanandroid.viewmodel.HomeListViewModel

class HomeListAdapter(val homeListViewModel: HomeListViewModel) :
    PagedListAdapter<ArticleEntity, RecyclerView.ViewHolder>(DiffCallback) {

    //    网络状态
    private var networkStatus: NetworkStatus? = null

    //    是否显示‘正在加载‘
    private var hasFooter = false

    init {
//        尝试重新加载画面
        homeListViewModel.retry()
    }

    //    更新网络状态
    fun updateNetworkStatus(networkStatus: NetworkStatus?) {
        this.networkStatus = networkStatus
//    若初次加载，则隐藏’正在加载‘
        if (networkStatus == NetworkStatus.INITIAL_LOADING) hideFooter() else showFooter()
    }

//    获取网络状态
    fun getNetworkStatus() = networkStatus

    private fun hideFooter() {
        if (hasFooter) {
//            将recycleView的最后一项删除
            notifyItemRemoved(itemCount - 1)
        }
        hasFooter = false
    }

    private fun showFooter() {
        if (hasFooter) {
//            修改’正在加载‘状态
            notifyItemChanged(itemCount - 1)
        } else {
            hasFooter = true
//            加入’正在加载‘状态
            notifyItemInserted(itemCount - 1)
        }
    }

    //    若显示’正在加载‘则数量加一
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter) 2 else 1
    }

    //    根据位置获取view的类型
    override fun getItemViewType(position: Int): Int {
        return if (hasFooter && position == itemCount - 1) R.layout.gallery_footer
        else if (position == 0) R.layout.slideshow_layout
        else R.layout.article_item_layout
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.article_item_layout -> {
                val article = getItem(position-1) ?: return
                (holder as ArticleHolder).bindWithArticle(article)
            }
            R.layout.slideshow_layout -> {
                (holder as SlideShowHolder).bindWithViewModel(homeListViewModel)
            }
            else -> (holder as FooterViewHolder).bindWithNetworkStatus(
                networkStatus
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.article_item_layout -> ArticleHolder.newInstance(parent)
            R.layout.slideshow_layout -> SlideShowHolder.newInstance(parent)
            else -> FooterViewHolder.newInstance(parent).also {
                it.itemView.setOnClickListener {
                    homeListViewModel.retry()
                }
            }
        }
    }
//      底部正在加载控件适配器
    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun newInstance(parent: ViewGroup): FooterViewHolder {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.gallery_footer, parent, false)
                return FooterViewHolder(view)
            }
        }

        fun bindWithNetworkStatus(networkStatus: NetworkStatus?) {
            with(itemView) {
                val textView = findViewById<TextView>(R.id.load_text)
                val progressBar = findViewById<ProgressBar>(R.id.loading)
                when (networkStatus) {
                    NetworkStatus.FAILED -> {
                        textView.text = "点击重试"
                        progressBar.visibility = View.GONE
                        isClickable = true
                    }
                    NetworkStatus.COMPLETED -> {
                        textView.text = "加载完毕"
                        progressBar.visibility = View.GONE
                        isClickable = false
                    }
                    else -> {
                        textView.text = "正在加载"
                        progressBar.visibility = View.VISIBLE
                        isClickable = false
                    }
                }
            }
        }
    }
//      轮播图适配器
    class SlideShowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun newInstance(parent: ViewGroup): SlideShowHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.slideshow_layout, parent, false)
                val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager)
                viewPager2.adapter = SlideShowAdapter()
                return SlideShowHolder(view)
            }
        }

        fun bindWithViewModel(homeListViewModel: HomeListViewModel) {
            with(itemView) {
                val viewPager = findViewById<ViewPager2>(R.id.viewPager)
                val adapter = viewPager.adapter as SlideShowAdapter
                homeListViewModel.viewPager2 = viewPager
                homeListViewModel.getBanners(adapter = adapter)
            }
        }

    }

    //    文章适配器
    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun newInstance(parent: ViewGroup): ArticleHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.article_item_layout, parent, false)
                return ArticleHolder(view)
            }
        }

        fun bindWithArticle(articleEntity: ArticleEntity) {
            with(itemView) {
                val author = findViewById<TextView>(R.id.author_item)
                val date = findViewById<TextView>(R.id.date_item)
                val title = findViewById<TextView>(R.id.title_item)
                val chapterName = findViewById<TextView>(R.id.chapterName_itme)
                val toTop = findViewById<ImageView>(R.id.toTop_item)
                val refresh = findViewById<ImageView>(R.id.refresh_item)
                val thumb = findViewById<ImageView>(R.id.thumb_item)
                articleEntity.let{
                    author.text = if (it.author == "")it.shareUser else it.author
                    date.text = it.niceDate
                    title.text = it.title
                    chapterName.text = it.chapterName
                    toTop.visibility = if (it.isTop)View.VISIBLE else View.GONE
                    refresh.visibility = if (it.fresh)View.VISIBLE else View.GONE
                    thumb.setImageResource(if (it.collect)R.drawable.ic_thumb_fill else R.drawable.ic_thumb)
                    val bundle = Bundle()
                    bundle.putString("url",articleEntity.link)
                    this.setOnClickListener {
                        this.findNavController().navigate(R.id.action_homeListFragment_to_articleFragment,bundle)
                    }
                }

            }
        }

    }


    object DiffCallback : DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem.chapterId == newItem.chapterId
        }
    }

}