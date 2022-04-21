package com.example.wanandroid.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wanandroid.adapter.PubViewPagerAdapter
import com.example.wanandroid.databinding.FragmentPublicBinding
import com.example.wanandroid.viewmodel.PublicViewModel
import com.google.android.material.tabs.TabLayoutMediator


class PublicFragment : Fragment() {

    private lateinit var _binding : FragmentPublicBinding

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel by activityViewModels<PublicViewModel>()

        _binding = FragmentPublicBinding.inflate(inflater,container,false)

        val data = viewModel.author

        val adapter = PubViewPagerAdapter(data,viewLifecycleOwner,requireContext())
        viewModel.getWxArticle(adapter)

        binding.viewpager.adapter = adapter

        data.observe(viewLifecycleOwner) {
            TabLayoutMediator(binding.tab, binding.viewpager) { tab, pos ->
                tab.text = data.value?.get(pos)?.name
            }.attach()
        }




        return binding.root
    }


}