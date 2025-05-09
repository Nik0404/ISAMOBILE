package com.example.isa.di.app;

import com.example.isa.di.global.ComponentHolder
import com.example.isa.di.global.scope.PerApplication
import dagger.Component;

@PerApplication
@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectComponentsHolder(holder: ComponentHolder)
}
