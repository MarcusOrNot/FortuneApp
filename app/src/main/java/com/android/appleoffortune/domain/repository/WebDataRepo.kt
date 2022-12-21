package com.android.appleoffortune.domain.repository

interface WebDataRepo {

    public fun getWebLink() : String?

    public fun setWebLink(linkUrl:String?)

}