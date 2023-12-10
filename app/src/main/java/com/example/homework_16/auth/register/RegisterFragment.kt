package com.example.homework_16.auth.register

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework_16.BaseFragment
import com.example.homework_16.auth.AuthErrorMessage
import com.example.homework_16.databinding.FragmentRegisterBinding
import com.example.homework_16.model.User


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private var passwordIsShow: Boolean = true
    private val viewModel: RegisterViewModel by viewModels()

    override fun listener() {
        showPassword()
        register()
    }

    private fun showPassword() {
        with(binding) {
            ivEye.setOnClickListener {
                if (passwordIsShow) {
                    etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    passwordIsShow = false
                } else {
                    etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                    passwordIsShow = true
                }
            }
        }
    }

    private fun register() {
        with(binding) {
            btnRegister.setOnClickListener {
                val user =
                    User(email = etEmail.text.toString(), password = etPassword.text.toString())

                if(etEmail.text.isNullOrBlank()){
                    tvError.text = AuthErrorMessage.EMPTY_EMAIL.message
                }else if(etPassword.text.isNullOrBlank()){
                    tvError.text = AuthErrorMessage.EMPTY_PASSWORD.message
                }else{
                    tvError.text = ""
                    viewModel.register(user)
                    findNavController().popBackStack()
                }
            }
        }
    }

}