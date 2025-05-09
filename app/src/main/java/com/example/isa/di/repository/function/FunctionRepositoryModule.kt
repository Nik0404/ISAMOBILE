package com.example.readerqrtmc.di.repository.function

import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.repositories.FunctionRepositoryImpl
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.api.FunctionApiModule
import com.example.isa.domain.repositories.FunctionRepository
import com.example.isa.domain.repositories.PreferencesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [FunctionApiModule::class])
class FunctionRepositoryModule {

    @PerScreen
    @Provides
    fun provideQrCodeRepository(
        functionApi: FunctionsApi,
        preferencesRepository: PreferencesRepository
    ): FunctionRepository {
        return FunctionRepositoryImpl(functionApi, preferencesRepository)
    }
}