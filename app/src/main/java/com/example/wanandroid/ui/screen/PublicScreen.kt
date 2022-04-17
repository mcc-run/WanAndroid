package com.example.wanandroid.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wanandroid.databinding.ScreenPublicBinding
import com.example.wanandroid.viewmodel.PublicViewModel

class PublicScreen : Fragment() {

    private var _binding : ScreenPublicBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PublicScreen()
    }

    private lateinit var viewModel: PublicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PublicViewModel::class.java)

        _binding = ScreenPublicBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.toolbarPublic.titleLabel.text = "公众号"
        binding.toolbarPublic.titleSearch.setOnClickListener{
            Toast.makeText((activity as Context),"您在公众号页面点击了搜索按钮", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}