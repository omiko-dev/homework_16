package com.example.homework_16.auth.register

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.homework_16.BaseFragment
import com.example.homework_16.auth.AuthViewModel
import com.example.homework_16.databinding.FragmentRegisterBinding
import com.example.homework_16.dto.AuthDto
import com.example.homework_16.service.AuthErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private var passwordIsShow: Boolean = true
    private val viewModel: AuthViewModel by viewModels()
    private val emailRegex: Regex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

    override fun listener() {
        showPassword()
        register()
    }

    override fun setUpObserver() {
//        TODO("Not yet implemented")
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
                val authDto =
                    AuthDto(
                        email = etEmail.text.toString().trim(),
                        password = etPassword.text.toString().trim()
                    )

                if (checkValid(authDto)) {
                    viewModel.register(authDto)
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun checkValid(authDto: AuthDto): Boolean {
        with(binding) {
            if (authDto.password.isBlank() || authDto.email.isBlank()) {
                tvError.text = AuthErrorMessage.EMPTY_FIELDS.msg
                return false
            }
            if (!emailRegex.matches(authDto.email)) {
                tvError.text = AuthErrorMessage.INCORRECT_EMAIL.msg
                return false
            }
            tvError.text = ""
            return true
        }
    }

}