package com.example.isa.di.repository.api

import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.di.global.scope.PerScreen
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FunctionApiModule {

    @PerScreen
    @Provides
    fun provideFunctionApi(retrofit: Retrofit): FunctionsApi {
        return retrofit.create(FunctionsApi::class.java)
    }

}