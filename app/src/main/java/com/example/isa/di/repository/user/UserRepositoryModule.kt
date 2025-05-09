package com.example.isa.di.repository.user

import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.data.datasource.database.dao.UserDao
import com.example.isa.data.datasource.network.UserApi
import com.example.isa.data.repositories.UserRepositoryImpl
import com.example.isa.di.global.scope.PerScreen
import dagger.Module
import dagger.Provides
import com.example.isa.di.repository.api.UserApiModule
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.domain.repositories.UserRepository

@Module(includes = [UserApiModule::class])
class UserRepositoryModule {

    @PerScreen
    @Provides
    fun provideUserRepository(
        userApi: UserApi,
        userDao: UserDao,
        preferencesRepository: PreferencesRepository
    ): UserRepository {
        return UserRepositoryImpl(userApi, userDao, preferencesRepository)
    }

    @PerScreen
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}