package com.example.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid.R
import com.example.wanandroid.viewmodel.ArticleViewModel


class ArticleFragment : Fragment() {

    companion object {
        fun newInstance() = ArticleFragment()
    }

    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.article_fragment, container, false)
        //获得控件
        val webView = inflate.findViewById(R.id.WebView_article) as WebView
//        获得网页
        val url = savedInstanceState?.getString("url") ?: "https://www.baidu.com"
        //访问网页
        webView.loadUrl(url)
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //使用WebView加载显示url
                view.loadUrl(url)
                //返回true
                return true
            }
        }
        return inflate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}