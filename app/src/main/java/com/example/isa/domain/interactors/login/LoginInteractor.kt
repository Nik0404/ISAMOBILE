package com.example.isa.domain.interactors.login

import io.reactivex.Single

interface LoginInteractor {
    fun loginUser(login: String, password: String): Single<Long>

    fun getLogin(): String

    fun getPassword(): String

    fun getSessionId(): String

    fun getAppVersionName(): String

    fun getDeviceId(): String

    fun setDeviceId(deviceId: String)

}