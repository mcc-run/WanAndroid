package com.example.wanandroid.ui.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid.databinding.ScreenMyBinding

import com.example.wanandroid.viewmodel.MyViewModel

class MyScreen : Fragment() {

    private var _binding: ScreenMyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MyViewModel::class.java)

        _binding = ScreenMyBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.toolbarMy.titleLabel.text = "我的"
        binding.toolbarMy.titleSearch.setOnClickListener{
            Toast.makeText((activity as Context),"您在我的页面点击了搜索按钮", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}