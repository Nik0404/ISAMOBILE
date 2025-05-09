package com.example.isa.di.repository.session

import com.example.isa.data.util.mappers.FunctionMapper
import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.data.datasource.database.dao.FunctionDao
import com.example.isa.data.repositories.SessionRepositoryImpl
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.api.SessionApiModule
import com.example.isa.domain.entity.local.database.UserFunction
import com.example.isa.domain.entity.remote.schema.FunctionSchema
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.SessionRepository
import dagger.Module
import dagger.Provides
import com.example.isa.data.datasource.network.SessionApi as SessionApi1

@Module(includes = [SessionApiModule::class])
class SessionRepositoryModule {

    @PerScreen
    @Provides
    fun provideSessionRepository(
        sessionApi: SessionApi1,
        userFunctionDao: FunctionDao,
        userFunctionMapper: EntityMapper<FunctionSchema, UserFunction>,
        preferencesRepository: PreferencesRepository,

        ): SessionRepository {
        return SessionRepositoryImpl(
            sessionApi,
            userFunctionDao,
            preferencesRepository,
            userFunctionMapper
        )
    }

    @PerScreen
    @Provides
    fun provideFunctionDao(appDatabase: AppDatabase): FunctionDao {
        return appDatabase.userFunctionDao()
    }

    @PerScreen
    @Provides
    fun provideFunctionMapper(): EntityMapper<FunctionSchema, UserFunction> {
        return FunctionMapper()
    }

}