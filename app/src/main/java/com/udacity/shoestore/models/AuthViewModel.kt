package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class AuthType() {
    Registration,
    Login,
}

enum class LoginState() {
    Registered,
    LoggedIn,
    Guest
}


class AuthViewModel : ViewModel() {
    private var _emailText = MutableLiveData("")
    val emailText: LiveData<String>
        get() = _emailText

    private var _passwordText = MutableLiveData("")
    val passwordText: LiveData<String>
        get() = _passwordText

    private var _state = MutableLiveData(LoginState.Guest)
    val state: LiveData<LoginState>
        get() = _state

    // No need for init

    fun onAuthentication(
        email: String,
        password: String,
        type: AuthType = AuthType.Login
    ) {
        if (email.isNotEmpty() || password.isNotEmpty()) {
            _emailText.value = email
            _passwordText.value = password
            if (type == AuthType.Registration) {
                _state.value = LoginState.Registered
            } else {
                _state.value = LoginState.LoggedIn
            }
        }
    }

    fun onEventAuthComplete() {
        _state.value = LoginState.Guest
    }
}