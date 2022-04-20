package com.example.wanandroid.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wanandroid.R
import com.example.wanandroid.adapter.SysSysDetailAdapter
import com.example.wanandroid.databinding.FragmentSysSysDetailBinding
import com.example.wanandroid.entity.Children
import com.example.wanandroid.viewmodel.SystemViewModel
import com.google.android.material.tabs.TabLayoutMediator


class SysSysDetailFragment : Fragment() {

    private lateinit var _binding : FragmentSysSysDetailBinding

    val binding get() = _binding

    val model by activityViewModels<SystemViewModel>()

    var pos = 61

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSysSysDetailBinding.inflate(inflater,container,false)
        val root = binding.root

        pos = arguments?.getInt("pos1",61)!!

        val children : List<Children> = model.chapters.value!![pos].children

        val adapter = SysSysDetailAdapter(children,viewLifecycleOwner,requireContext())

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tab,binding.viewPager){
            tab,pos ->
            tab.text = children[pos].name
        }.attach()

        return root
    }

}