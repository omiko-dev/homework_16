package com.example.homework_16.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_16.auth.AuthNetwork
import com.example.homework_16.model.User
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {


    fun login(user: User){
        try {
            viewModelScope.launch {
                val response = AuthNetwork.authService().login(user)
                if(response.isSuccessful){
                    Log.i("omiko", "${response.body()}")
                }else{
                    Log.i("omiko", response.body().toString())
                }
            }
        }catch (e: Exception){
            Log.i("omiko", e.message.toString())
        }
    }

}