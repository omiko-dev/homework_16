package com.example.homework_16.auth.login

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework_16.BaseFragment
import com.example.homework_16.auth.AuthViewModel
import com.example.homework_16.databinding.FragmentLoginBinding
import com.example.homework_16.dto.AuthDto
import com.example.homework_16.service.AuthErrorMessage
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val emailRegex: Regex = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    private val viewModel: AuthViewModel by viewModels()

    override fun listener() {
        showPassword()
        login()
    }

    override fun setUpObserver() {
        userObserver()
    }

    private fun showPassword() {
        with(binding) {
            ivEye.setOnClickListener {
                if (etPassword.inputType == 129) {
                    etPassword.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
            }
        }
    }

    private fun login() {
        with(binding) {
            btnLogin.setOnClickListener {
                val authDto =
                    AuthDto(
                        email = etEmail.text.toString().trim(),
                        password = etPassword.text.toString().trim()
                    )

                if (checkValid(authDto)) {
                    viewModel.login(authDto)
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


    private fun userObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userSharedFlow.collect {
                    if (it == null) {
                        binding.tvError.text = AuthErrorMessage.INCORRECT_DATA.msg
                    }
                }
            }
        }
    }
}