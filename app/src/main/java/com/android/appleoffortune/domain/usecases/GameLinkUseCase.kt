package com.android.appleoffortune.domain.usecases

import com.android.appleoffortune.domain.repository.LinkDataRepo

class GameLinkUseCase(private val _gameLinkRepo:LinkDataRepo) {

    public suspend fun GetGameLink():String {
        return _gameLinkRepo.GetGameLink()
    }

}