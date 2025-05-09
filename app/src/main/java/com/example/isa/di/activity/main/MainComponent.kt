package com.example.isa.di.activity.main

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.activity.main.MainActivity
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [MainModule::class])
interface MainComponent : BaseComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<MainComponent, MainModule>

}

