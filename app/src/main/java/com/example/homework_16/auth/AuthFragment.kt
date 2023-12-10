package com.example.homework_16.auth

import androidx.navigation.fragment.findNavController
import com.example.homework_16.BaseFragment
import com.example.homework_16.databinding.FragmentAuthBinding


class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    override fun listener() {
        goToRegister()
        goToLogin()
    }


    private fun goToRegister(){
        binding.btnRegister.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun goToLogin(){
        binding.btnLogin.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}

enum class AuthErrorMessage(val message: String){
    EMPTY_EMAIL("Email Is Empty"),
    EMPTY_PASSWORD("Password Is Empty")
}