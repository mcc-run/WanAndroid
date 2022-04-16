package com.example.wanandroid.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.adapter.HomeListAdapter
import com.example.wanandroid.adapter.SlideShowAdapter
import com.example.wanandroid.databinding.HomeListFragmentBinding
import com.example.wanandroid.datasource.NetworkStatus
import com.example.wanandroid.viewmodel.HomeListViewModel

class HomeListFragment : Fragment() {

    private var _binding: HomeListFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var homeListViewModel: HomeListViewModel

    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeListViewModel = ViewModelProvider(this)[HomeListViewModel::class.java]

        _binding = HomeListFragmentBinding.inflate(inflater, container, false)
//      设置标题栏：标题、点击监听事件
        setTitle()
//        初始化文章列表
        val homeListAdapter = initRecycleView()

//        刷新数据
        refreshData(homeListAdapter)

        return binding.root
    }

    private fun initRecycleView(): HomeListAdapter {
        //        配置适配器
        val homeListAdapter = HomeListAdapter(homeListViewModel)
        binding.recycleViewHomeList.apply {
            adapter = homeListAdapter
            val manager = LinearLayoutManager(requireContext())
            layoutManager = manager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    //获取RecyclerView当前顶部显示的第一个条目对应的索引
                    val position: Int = manager.findLastVisibleItemPosition()
                    if (position < 10) binding.toTop.visibility =
                        View.GONE else binding.toTop.visibility =
                        View.VISIBLE
                }
            })

        }
        //        置顶按钮
        binding.toTop.setOnClickListener {
            binding.recycleViewHomeList.post(Runnable {
                scrollToTop()
            })
        }
        //        给文章列表添加观察者
        homeListViewModel.articleList.observe(viewLifecycleOwner, Observer {
            homeListAdapter.submitList(it)
        })
        //        给轮播图添加计时器，每两秒转一次
        handler.postDelayed(object : Runnable {
            override fun run() {
                //获取当前的页面下标
                homeListViewModel.rotateBanner()
                handler.postDelayed(this, 2000)
            }
        }, 2000)
        return homeListAdapter
    }

    private fun refreshData(homeListAdapter: HomeListAdapter) {
        //    重置数据
        binding.swipeRefresh.setOnRefreshListener {
            homeListViewModel.resetQuery()
            scrollToTop()
        }
        homeListViewModel.networkStatus.observe(viewLifecycleOwner, Observer {
            if (homeListAdapter.getNetworkStatus() == NetworkStatus.INITIAL_LOADING &&
                        it == NetworkStatus.LOADED) {
                scrollToTop()
            }
            homeListAdapter.updateNetworkStatus(it)
            //            只有此次加载时，才显示刷新
            binding.swipeRefresh.isRefreshing = it == NetworkStatus.INITIAL_LOADING
        })
    }

    private fun scrollToTop() {
        binding.recycleViewHomeList.smoothScrollToPosition(0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private fun setTitle() {
        binding.titleHome.titleLabel.text = "首页"
        binding.titleHome.titleSearch.setOnClickListener {
            Toast.makeText(requireContext(), "您点击了首页搜索按钮", Toast.LENGTH_SHORT).show()
        }
    }

}