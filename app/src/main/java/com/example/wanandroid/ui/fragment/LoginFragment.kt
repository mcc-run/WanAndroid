package com.example.wanandroid.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.wanandroid.R
import com.example.wanandroid.databinding.FragmentLoginBinding
import com.example.wanandroid.viewmodel.MyViewModel


class LoginFragment : Fragment() {

    private lateinit var _binding : FragmentLoginBinding

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val model by activityViewModels<MyViewModel>()

        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        val view = binding.root

        binding.login.setOnClickListener {
            val username = binding.UserName.text.toString()
            val password = binding.password.text.toString()
            if (username == "" || password == ""){
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("账号或密码不能为空")
                    .setPositiveButton("确定") { _, _ -> }
                builder.create()
                builder.show()
            }
            else {
                model.login(username, password,requireContext())
            }
        }

        binding.regist.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        model.isLogin.postValue(false)

        model.isLogin.observe(viewLifecycleOwner){
            if (it == true)view.findNavController().navigate(R.id.action_loginFragment_to_myFragment)
        }

        return view
    }


}