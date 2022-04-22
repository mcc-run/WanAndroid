package com.example.wanandroid.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.wanandroid.R
import com.example.wanandroid.databinding.FragmentRegisterBinding
import com.example.wanandroid.viewmodel.MyViewModel


class RegisterFragment : Fragment() {

    private lateinit var _binding : FragmentRegisterBinding

    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        val view = binding.root

        val model by activityViewModels<MyViewModel>()

        binding.register.setOnClickListener {
            model.register(binding.UserName.text.toString(),binding.passWord.text.toString(),binding.confirm.text.toString(),requireContext())
        }

        model.isRegister.observe(viewLifecycleOwner){
            if (it)view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

}