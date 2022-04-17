package com.example.wanandroid.ui.screen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wanandroid.R
import com.example.wanandroid.adapter.SystemAdapter
import com.example.wanandroid.databinding.ScreenSystemBinding
import com.example.wanandroid.viewmodel.SystemViewModel
import com.google.android.material.tabs.TabLayoutMediator
import androidx.fragment.app.activityViewModels

class SystemScreen : Fragment() {

    private var _binding: ScreenSystemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val handle = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = ScreenSystemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.toolbarSystem.titleLabel.text = "体系"
        binding.toolbarSystem.titleSearch.setOnClickListener {
            Toast.makeText((activity as Context), "您在体系页面点击了搜索按钮", Toast.LENGTH_SHORT).show()
        }


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}