package com.example.wanandroid.ui.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
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

    private var url = "https://www.baidu.com"

    lateinit var webView : WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.article_fragment, container, false)
        //获得控件
        webView = inflate.findViewById(R.id.WebView_article) as WebView

        return inflate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        url = arguments?.getString("url","https://www.baidu.com").toString()
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }
        webView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action === KeyEvent.ACTION_DOWN) {
                //按返回键操作并且能回退网页
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    //后退
                    webView.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })
        webView.loadUrl(url)
        // TODO: Use the ViewModel
    }

}