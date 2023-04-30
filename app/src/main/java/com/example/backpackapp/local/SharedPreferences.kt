package com.example.backpackapp.local

import android.content.Context
import android.content.SharedPreferences
import com.example.backpackapp.units.Parameters.IS_EMPTY
import com.example.backpackapp.units.Parameters.URL

class SharedPreferences(mContext: Context, mKey: String) {
    companion object {
        const val DEFAULT_URL_AVATAR: String = "url_avatar"
        const val DEFAULT_SEE_ALL = 0
        const val DEFAULT_UID_USER: String = IS_EMPTY
    }

    private val sharedPreferences: SharedPreferences =
        mContext.getSharedPreferences(mKey, Context.MODE_PRIVATE)

    var urlAvatar: String?
        get() = sharedPreferences.getString(DEFAULT_URL_AVATAR, null)
        set(value) = sharedPreferences.edit().putString(DEFAULT_URL_AVATAR, value)
            .apply()

    var uidUser: String?
        get() = sharedPreferences.getString(DEFAULT_UID_USER, null)
        set(value) = sharedPreferences.edit().putString(DEFAULT_UID_USER, value).apply()

    var sellAllRequests: Int?
        get() = sharedPreferences.getInt(DEFAULT_SEE_ALL.toString(), 0)
        set(value) {
            value?.let { sharedPreferences.edit().putInt(DEFAULT_SEE_ALL.toString(), it).apply() }
        }
}