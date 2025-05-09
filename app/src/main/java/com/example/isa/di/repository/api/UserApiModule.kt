package com.example.isa.di.repository.api

import com.example.isa.data.datasource.network.UserApi
import com.example.isa.di.global.scope.PerScreen
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserApiModule {

    @PerScreen
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

}