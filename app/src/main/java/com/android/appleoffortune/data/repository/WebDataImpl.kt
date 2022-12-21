package com.android.appleoffortune.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.android.appleoffortune.domain.repository.WebDataRepo

class WebDataImpl(private val context:Context) : WebDataRepo {
    private val dataPrefs:SharedPreferences = context.getSharedPreferences("DataPrefs", Context.MODE_PRIVATE);

    override fun getWebLink(): String? {
        return dataPrefs.getString("WebLink", null)
    }

    override fun setWebLink(linkUrl: String?) {
        dataPrefs.edit().putString("WebLink", linkUrl).commit()
    }
}