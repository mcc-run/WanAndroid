package com.example.wanandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.wanandroid.R
import com.example.wanandroid.adapter.SystemAdapter
import com.example.wanandroid.databinding.FragmentSystemBinding
import com.example.wanandroid.viewmodel.SystemViewModel
import com.google.android.material.tabs.TabLayoutMediator

class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val systemViewModel =
            ViewModelProvider(this)[SystemViewModel::class.java]

        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.toolbarSystem.titleLabel.text = "体系"
        binding.toolbarSystem.titleSearch.setOnClickListener {
            Toast.makeText((activity as Context), "您在体系页面点击了搜索按钮", Toast.LENGTH_SHORT).show()
        }

        val systemAdapter = SystemAdapter(systemViewModel)
        binding.ViewPagerSys.adapter = systemAdapter


        TabLayoutMediator(binding.tabLayoutSys, binding.ViewPagerSys) { tab, position ->
            tab.text = systemViewModel.pages[position]
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}