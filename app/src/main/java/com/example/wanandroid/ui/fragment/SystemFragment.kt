package com.example.wanandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wanandroid.R
import com.example.wanandroid.adapter.SystemAdapter
import com.example.wanandroid.databinding.FragmentSystemBinding
import com.example.wanandroid.databinding.ScreenSystemBinding
import com.example.wanandroid.viewmodel.SystemViewModel
import com.google.android.material.tabs.TabLayoutMediator


class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val systemViewModel by activityViewModels<SystemViewModel>()

        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val systemAdapter = SystemAdapter(systemViewModel,viewLifecycleOwner)
        binding.ViewPagerSys.adapter = systemAdapter

        TabLayoutMediator(binding.tabLayoutSys, binding.ViewPagerSys) { tab, position ->
            tab.text = systemViewModel.pages[position]
        }.attach()

        return root

    }

}