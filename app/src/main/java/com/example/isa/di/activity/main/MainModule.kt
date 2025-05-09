package com.example.isa.di.activity.main

import android.content.Context
import com.example.isa.common.constants.utils.NetworkErrorsParser
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.api.SessionApiModule
import com.example.isa.di.repository.session.SessionRepositoryModule
import com.example.isa.di.repository.user.UserRepositoryModule
import com.example.isa.domain.interactors.main.MainInteractor
import com.example.isa.domain.interactors.main.MainInteractorImpl
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.SessionRepository
import com.example.isa.domain.repositories.UserRepository
import com.example.isa.presentation.activity.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module(
    includes = [UserRepositoryModule::class, SessionRepositoryModule::class, SessionApiModule::class]
)
class MainModule(private val applicationContext: Context) : BaseModule {

    @PerScreen
    @Provides
    fun providePresenter(
        mainInteractor: MainInteractor,
        networkErrorsParser: NetworkErrorsParser
    ): MainPresenter {
        return MainPresenter(mainInteractor, networkErrorsParser)
    }

    @PerScreen
    @Provides
    fun provideNetworkErrorParser(): NetworkErrorsParser {
        return NetworkErrorsParser()
    }


    @PerScreen
    @Provides
    fun provideMainInteractor(
        sessionRepository: SessionRepository,
        userRepository: UserRepository,
        preferencesRepository: PreferencesRepository,
        appDatabase: AppDatabase,
        schedulersProvider: SchedulersProvider
    ): MainInteractor {
        return MainInteractorImpl(
            sessionRepository,
            userRepository,
            preferencesRepository,
            appDatabase,
            schedulersProvider
        )
    }

}