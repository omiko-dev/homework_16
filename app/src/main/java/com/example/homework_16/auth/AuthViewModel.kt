package com.example.homework_16.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_16.dto.AuthDto
import com.example.homework_16.model.User
import com.example.homework_16.network.AuthNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private var _userShareFlow = MutableSharedFlow<User?>()
    val userSharedFlow = _userShareFlow.asSharedFlow()



    fun register(authDto: AuthDto){
        try {
            viewModelScope.launch {
                val response = AuthNetwork.authService().register(authDto = authDto)
                if(response.isSuccessful){
                    _userShareFlow.emit(response.body())
                }else{
                    _userShareFlow.emit(response.body())
                }
            }
        }catch (e: Exception){
            Log.i("exception", "${e.message}")
        }

    }

    fun login(authDto: AuthDto){
        try {
            viewModelScope.launch {
                val response = AuthNetwork.authService().login(authDto)
                if(response.isSuccessful){
                    _userShareFlow.emit(response.body())
                }else{
                    _userShareFlow.emit(response.body())
                }
            }
        }catch (e: Exception){
            Log.i("exception", e.message.toString())
        }
    }

}