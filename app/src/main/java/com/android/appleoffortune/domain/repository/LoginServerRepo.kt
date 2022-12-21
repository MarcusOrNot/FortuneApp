package com.android.appleoffortune.domain.repository

interface LoginServerRepo {

    public fun tryLogin(pass:String, result:LoginResult)

}