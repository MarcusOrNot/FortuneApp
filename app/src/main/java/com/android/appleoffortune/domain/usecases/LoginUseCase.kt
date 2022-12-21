package com.android.appleoffortune.domain.usecases

import com.android.appleoffortune.domain.repository.LoginResult
import com.android.appleoffortune.domain.repository.LoginServerRepo

class LoginUseCase(private val _loginServerRepo:LoginServerRepo, private val _onResult: LoginResult) {

    public fun Login(pass:String) {
        _loginServerRepo.tryLogin(pass, _onResult)
    }
}