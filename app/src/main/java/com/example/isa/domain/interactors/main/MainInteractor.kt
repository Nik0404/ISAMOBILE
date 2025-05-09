package com.example.isa.domain.interactors.main

import com.example.isa.domain.entity.local.database.User
import io.reactivex.Completable
import io.reactivex.Single

interface MainInteractor {

    fun refreshSessionId(): Completable

    fun logout(): Completable

    fun getCurrentUser(): Single<User>

    fun getAppVersionName(): String
}
