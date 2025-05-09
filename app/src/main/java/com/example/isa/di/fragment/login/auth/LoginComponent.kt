package com.example.isa.di.fragment.login.auth

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.login.auth.LoginFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent : BaseComponent<LoginFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<LoginComponent, LoginModule>
}