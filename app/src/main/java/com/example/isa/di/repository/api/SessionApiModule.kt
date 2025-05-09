package com.example.isa.di.repository.api

import com.example.isa.data.datasource.network.SessionApi
import com.example.isa.di.global.scope.PerScreen
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SessionApiModule {

    @PerScreen
    @Provides
    fun provideSessionApi(retrofit: Retrofit): SessionApi {
        return retrofit.create(SessionApi::class.java)
    }
}