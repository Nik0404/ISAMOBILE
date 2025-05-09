package com.example.isa.domain.interactors.main

import com.example.isa.common.constants.FunctionsConstants
import com.example.isa.common.constants.NetworkConstants
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.domain.entity.local.database.User
import com.example.isa.domain.entity.remote.request.LogoutRequest
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.SessionRepository
import com.example.isa.domain.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.Single

class MainInteractorImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val appDatabase: AppDatabase,
    private val schedulersProvider: SchedulersProvider
) : MainInteractor {

    override fun refreshSessionId(): Completable {
        return sessionRepository.loginUser(
            preferencesRepository.login,
            preferencesRepository.password,
            NetworkConstants.APP_LOGIN_HOST_CODE,
            getAppVersionName()
        ).subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.io())
            .flatMap {
                sessionRepository.registerFunction(
                    it, FunctionsConstants.PACKAGE_RECONCILIATION,
                    NetworkConstants.APP_PACKET_NEGOTIATION_CODE,
                    getAppVersionName()
                )
            }.ignoreElement()
            .observeOn(schedulersProvider.ui())
    }


    override fun logout(): Completable {
        val currentSessionId = preferencesRepository.authSessionId
        val logoutRequest = LogoutRequest(currentSessionId, NetworkConstants.APP_LOGIN_HOST_CODE)

        return sessionRepository.logout(logoutRequest)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.io())
            .doOnEvent {
                val versionApp = preferencesRepository.versionName

                preferencesRepository.clear()

                preferencesRepository.versionName = versionApp
            }
            .observeOn(schedulersProvider.ui())
    }

    override fun getCurrentUser(): Single<User> {
        return userRepository.getCurrentUser()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }

    override fun getAppVersionName(): String = preferencesRepository.versionName
}
