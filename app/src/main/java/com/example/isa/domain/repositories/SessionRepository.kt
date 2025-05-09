package com.example.isa.domain.repositories

import com.example.isa.domain.entity.local.database.UserFunction
import com.example.isa.domain.entity.remote.request.LogoutRequest
import io.reactivex.Completable
import io.reactivex.Single

interface SessionRepository {

    fun loginUser(
        login: String,
        password: String,
        appCode: String,
        appVersion: String
    ): Single<String>

    fun registerFunction(
        sessionId: String,
        functionId: Int,
        appCode: String,
        appVersion: String
    ): Single<String>

    fun getAppVersionName(): String

    fun getLogin(): String

    fun getPassword(): String

    fun getAuthSessionId(): String

    fun getFunctionId(): Int

    fun logout(logoutRequest: LogoutRequest): Completable

    fun getFunctions(): Single<List<UserFunction>>

}
