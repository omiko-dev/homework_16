package com.example.homework_16.auth.login

import android.text.InputType
import android.util.Log
import com.example.homework_16.BaseFragment
import com.example.homework_16.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {



    override fun listener() {
        showPassword()
    }

    private fun showPassword(){
        with(binding){
            ivEye.setOnClickListener {
                if(etPassword.inputType == 129){
                    etPassword.inputType = InputType.TYPE_CLASS_TEXT
                }else{
                    etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
            }
        }
    }
}