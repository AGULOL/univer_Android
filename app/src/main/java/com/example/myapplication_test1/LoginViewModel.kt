package com.example.myapplication_test1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(login: String, password: String) {
        val trimmedLogin = login.trim()

        if (trimmedLogin.isEmpty() || password.isEmpty()) {
            _loginResult.value = LoginResult.EmptyFields
            return
        }

        val correctLogin = "1"
        val correctPassword = "1"

        if (trimmedLogin == correctLogin && password == correctPassword) {
            _loginResult.value = LoginResult.Success
        } else {
            _loginResult.value = LoginResult.Error("Неверный логин или пароль")
        }
    }
}

sealed class LoginResult {
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
    object EmptyFields : LoginResult()
}
