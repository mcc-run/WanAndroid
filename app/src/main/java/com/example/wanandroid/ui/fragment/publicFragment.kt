package com.example.wanandroid.ui.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wanandroid.databinding.FragmentPublicBinding
import com.example.wanandroid.viewmodel.PublicViewModel

class publicFragment : Fragment() {

    private var _binding : FragmentPublicBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = publicFragment()
    }

    private lateinit var viewModel: PublicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PublicViewModel::class.java)

        _binding = FragmentPublicBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.toolbarPublic.titleLabel.text = "体系"
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