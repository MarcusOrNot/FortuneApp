package com.android.appleoffortune.domain.usecases

import com.android.appleoffortune.domain.repository.WebDataRepo

class WebDataUseCase(private val _webDataRepo: WebDataRepo) {

    public fun getLink():String? {
        return _webDataRepo.getWebLink()
    }

    public fun setLink(link:String) {
        _webDataRepo.setWebLink(link)
    }
}