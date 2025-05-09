package com.example.isa.di.repository.packettask

import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.data.datasource.database.dao.PacketTaskDao
import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.repositories.PacketBaseRepositoryImpl
import com.example.isa.data.util.mappers.PacketFunctionMapper
import com.example.isa.data.util.mappers.PacketTaskMapper
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.repositories.PacketBaseRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PacketTaskRepositoryModule {

    @PerScreen
    @Provides
    fun providePacketBaseRepository(
        functionsApi: FunctionsApi,
        packetTaskDao: PacketTaskDao,
        packetTaskMapper: PacketTaskMapper,
        packetFunctionMapper: PacketFunctionMapper
    ): PacketBaseRepository {
        return PacketBaseRepositoryImpl(
            functionsApi,
            packetTaskDao,
            packetTaskMapper,
            packetFunctionMapper
        )
    }

    @PerScreen
    @Provides
    fun providePacketTaskDao(appDatabase: AppDatabase): PacketTaskDao {
        return appDatabase.packetTaskDao()
    }

    @PerScreen
    @Provides
    fun provideFunctionApi(retrofit: Retrofit): FunctionsApi {
        return retrofit.create(FunctionsApi::class.java)
    }

    @PerScreen
    @Provides
    fun providePacketTaskMapper(): PacketTaskMapper {
        return PacketTaskMapper()
    }

    @PerScreen
    @Provides
    fun providePacketFunctionMapper(): PacketFunctionMapper {
        return PacketFunctionMapper()
    }
}