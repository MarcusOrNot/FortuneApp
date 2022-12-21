package com.android.appleoffortune.domain.repository

interface LinkDataRepo {

    public suspend fun GetGameLink():String;

}