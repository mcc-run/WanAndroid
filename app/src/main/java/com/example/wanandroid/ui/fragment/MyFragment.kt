package com.example.wanandroid.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.wanandroid.R
import com.example.wanandroid.constan.UserMessage
import com.example.wanandroid.databinding.FragmentMyBinding
import com.example.wanandroid.viewmodel.MyViewModel


class MyFragment : Fragment() {

    lateinit var _binding : FragmentMyBinding

    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val model by activityViewModels<MyViewModel>()

        _binding = FragmentMyBinding.inflate(inflater,container,false)
        val view = binding.root

        if (UserMessage.userMsg.id != -1){
            model.isLogin.postValue(true)
        }else {
            model.isLogin.postValue(false)
        }

        model.isLogin.observe(viewLifecycleOwner){
            if (it){
                binding.unLogin.visibility = View.GONE
                binding.head.visibility = View.VISIBLE
                binding.userName.visibility = View.VISIBLE
                binding.detail.visibility = View.VISIBLE
                binding.userName.text = UserMessage.userMsg.username
                binding.userId.text = UserMessage.userMsg.id.toString()
                binding.Email.text = UserMessage.userBean.email
                binding.loginOut.setOnClickListener {
                    model.logOut(requireContext())
                }
            }else {
                binding.detail.visibility = View.GONE
                binding.unLogin.visibility = View.VISIBLE
                binding.head.visibility = View.GONE
                binding.userName.visibility = View.GONE
            }
        }

        binding.UserMsg.setOnClickListener {
            if(!model.isLogin.value!!)it.findNavController().navigate(R.id.action_myFragment_to_loginFragment)
        }

        return view
    }


}