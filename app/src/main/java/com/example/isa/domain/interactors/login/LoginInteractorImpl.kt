package com.example.isa.domain.interactors.login

import com.example.isa.common.constants.NetworkConstants
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.SessionRepository
import com.example.isa.domain.repositories.UserRepository
import io.reactivex.Single

class LoginInteractorImpl(
    private val sessionRepository: SessionRepository,
    private val preferencesRepository: PreferencesRepository,
    private val schedulersProvider: SchedulersProvider,
    private val userRepository: UserRepository
) : LoginInteractor {

    override fun loginUser(
        login: String,
        password: String,
    ): Single<Long> {
        return sessionRepository.loginUser(
            login,
            password,
            NetworkConstants.APP_LOGIN_HOST_CODE,
            getAppVersionName()
        )
            .subscribeOn(schedulersProvider.io())
            .flatMap { userRepository.loadUserInfo(login, it) }
            .observeOn(schedulersProvider.ui())
    }

    override fun getLogin(): String = sessionRepository.getLogin()

    override fun getPassword(): String = sessionRepository.getPassword()

    override fun getSessionId(): String = sessionRepository.getAuthSessionId()

    override fun getAppVersionName(): String = sessionRepository.getAppVersionName()

    override fun getDeviceId(): String = preferencesRepository.deviceId

    override fun setDeviceId(deviceId: String) {
        preferencesRepository.deviceId = deviceId
    }
}
