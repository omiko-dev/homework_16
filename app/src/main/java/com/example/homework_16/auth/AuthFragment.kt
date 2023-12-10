package com.example.homework_16.auth

import androidx.navigation.fragment.findNavController
import com.example.homework_16.BaseFragment
import com.example.homework_16.databinding.FragmentAuthBinding


class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    override fun listener() {
        goToRegister()
        goToLogin()
    }
    override fun setUpObserver(){}
    override fun bind() { }

    private fun goToRegister(){
        binding.btnRegister.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun goToLogin(){
        binding.btnLogin.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToLoginFragment(null)
            findNavController().navigate(action)
        }
    }
}

