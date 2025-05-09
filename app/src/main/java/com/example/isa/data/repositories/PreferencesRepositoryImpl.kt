package com.example.isa.data.repositories

import android.content.Context
import com.example.isa.common.constants.PreferencesConstants
import com.example.isa.domain.repositories.PreferencesRepository


class PreferencesRepositoryImpl(context: Context) : PreferencesRepository {

    private val storage =
        context.getSharedPreferences(PreferencesConstants.APP_PREFERENCES, Context.MODE_PRIVATE)

    init {
        val pakageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val appVersionName = pakageInfo.versionName
        saveVersionName(appVersionName!!)
    }

    private fun saveVersionName(appVersionName: String) {
        storage.edit().putString(PreferencesConstants.VERSION_NAME, appVersionName).apply()
    }

    override var login: String
        get() = storage.getString(PreferencesConstants.LOGIN, "")!!
        set(value) {
            storage.edit().putString(PreferencesConstants.LOGIN, value).apply()
        }

    override var password: String
        get() = storage.getString(PreferencesConstants.PASSWORD, "")!!
        set(value) {
            storage.edit().putString(PreferencesConstants.PASSWORD, value).apply()
        }

    override var versionName: String
        get() = storage.getString(PreferencesConstants.VERSION_NAME, "")!!
        set(value) {
            saveVersionName(value)
        }

    override var deviceId: String
        get() = storage.getString(PreferencesConstants.DEVICE_ID, "")!!
        set(value) = storage.edit().putString(PreferencesConstants.DEVICE_ID, value).apply()

    override val userId: Int
        get() = 1

    override var authSessionId: String
        get() = storage.getString(PreferencesConstants.AUTH_SESSION_ID, "")!!
        set(value) {
            storage.edit().putString(PreferencesConstants.AUTH_SESSION_ID, value).apply()
        }

    override var functionSessionId: String
        get() = storage.getString(PreferencesConstants.SESSION_ID, "")!!
        set(value) {
            storage.edit().putString(PreferencesConstants.SESSION_ID, value).apply()
        }

    override var functionPacketNegotiationId: Int
        get() = storage.getInt(PreferencesConstants.FUNCTION_PACKET_NEGOTIATION_ID, -1)
        set(value) {
            storage.edit().putInt(PreferencesConstants.FUNCTION_PACKET_NEGOTIATION_ID, value)
                .apply()
        }

    override var functionSessionPacketNegotiationId: String
        get() = storage.getString(PreferencesConstants.FUNCTION_PACKET_SESSION_NEGOTIATION_ID, "")!!
        set(value) {
            storage.edit()
                .putString(PreferencesConstants.FUNCTION_PACKET_SESSION_NEGOTIATION_ID, value)
                .apply()
        }

    override fun clear() {
        storage.edit().clear().apply()
    }
}
