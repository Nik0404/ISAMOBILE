package com.example.isa.di.fragment.login.auth

import com.example.isa.common.constants.utils.NetworkErrorsParser
import com.example.isa.di.fragment.login.BaseLoginModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.interactors.login.LoginInteractor
import com.example.isa.presentation.fragment.login.auth.LoginPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [BaseLoginModule::class])
class LoginModule : BaseModule {

    @PerScreen
    @Provides
    fun providePresenter(
        loginInteractor: LoginInteractor,
        networkErrorsParser: NetworkErrorsParser
    ): LoginPresenter {

        return LoginPresenter(loginInteractor, networkErrorsParser)
    }
}