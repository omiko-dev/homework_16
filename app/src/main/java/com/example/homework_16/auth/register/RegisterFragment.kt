package com.example.homework_16.auth.register

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework_16.BaseFragment
import com.example.homework_16.auth.AuthViewModel
import com.example.homework_16.databinding.FragmentRegisterBinding
import com.example.homework_16.dto.AuthDto
import com.example.homework_16.service.AuthErrorMessage
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: AuthViewModel by viewModels()
    private val emailRegex: Regex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

    override fun listener() {
        showPassword()
        register()
    }

    override fun setUpObserver() {
        userObserver()
    }

    override fun bind() { }

    private fun showPassword() {
        with(binding) {
            ivEye.setOnClickListener {
                if (etPassword.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    etPassword.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    etPassword.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
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

    private fun userObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userSharedFlow.collect {
                    it?.let {
                        with(binding) {
                            val email = etEmail.text.toString()
                            val action =
                                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(email)
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }

}