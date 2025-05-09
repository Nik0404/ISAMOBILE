package com.example.isa.di.fragment.login

import com.example.isa.common.constants.utils.NetworkErrorsParser
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.session.SessionRepositoryModule
import com.example.isa.di.repository.user.UserRepositoryModule
import com.example.isa.domain.interactors.login.LoginInteractor
import com.example.isa.domain.interactors.login.LoginInteractorImpl
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.SessionRepository
import com.example.isa.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides

@Module(includes = [SessionRepositoryModule::class, UserRepositoryModule::class])
class BaseLoginModule {

    @PerScreen
    @Provides
    fun provideLoginInteractor(
        sessionRepository: SessionRepository,
        preferencesRepository: PreferencesRepository,
        schedulersProvider: SchedulersProvider, userRepository: UserRepository
    ): LoginInteractor {
        return LoginInteractorImpl(
            sessionRepository,
            preferencesRepository,
            schedulersProvider,
            userRepository
        )
    }

    @PerScreen
    @Provides
    fun provideNetworkErrorParser(): NetworkErrorsParser {
        return NetworkErrorsParser()
    }
}