package com.example.homework_16.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_16.auth.AuthNetwork
import com.example.homework_16.model.User
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {


    fun register(user: User){
        try {
            viewModelScope.launch {
                val response = AuthNetwork.authService().register(user = user)
                if(response.isSuccessful){
                    Log.i("omiko", "${response.body()}")
                }else{
                    Log.i("omiko", "no_omiko")
                }
            }
        }catch (e: Exception){
            Log.i("omiko", "${e.message}")
        }

    }

}