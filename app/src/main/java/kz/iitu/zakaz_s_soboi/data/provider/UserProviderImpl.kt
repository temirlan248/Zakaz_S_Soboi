package kz.iitu.zakaz_s_soboi.data.provider

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserProvider {
    companion object {
        private const val PREF_NAME = "TODO_APPLICATION"
        private const val KEY_AUTH_TOKEN = "KEY_AUTH_TOKEN"
        private const val UN_LOGGED_USER_ID = -1
    }

    private var sharedPreferences: SharedPreferences? = null
        get() {
            if (field == null)
                field = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return field
        }

    override fun getToken(): Int = sharedPreferences?.getInt(
        KEY_AUTH_TOKEN, UN_LOGGED_USER_ID
    ) ?: UN_LOGGED_USER_ID

    override fun saveToken(token: Int?) {
        val preferences = sharedPreferences
        val editor = preferences?.edit()
        editor?.putInt(KEY_AUTH_TOKEN, token ?: UN_LOGGED_USER_ID)
        editor?.apply()
    }
}
