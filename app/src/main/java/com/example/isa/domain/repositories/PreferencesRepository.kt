package com.example.isa.domain.repositories

interface PreferencesRepository {
    var login: String
    var password: String

    var versionName: String
    var deviceId: String

    val userId: Int

    var authSessionId: String

    var functionSessionId: String

    var functionSessionPacketNegotiationId: String

    var functionPacketNegotiationId: Int

    fun clear()
}