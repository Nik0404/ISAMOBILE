package com.example.isa.di.repository.packetnegotiation

import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.data.datasource.database.dao.PacketNegotiationDao
import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.repositories.PacketNegotiationRepositoryImpl
import com.example.isa.data.util.mappers.PacketNegotiationMapper
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.repositories.PacketNegotiationRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PacketNegotiationRepositoryModule {

    @PerScreen
    @Provides
    fun providePacketNegotiationRepository(
        functionsApi: FunctionsApi,
        packetNegotiationDao: PacketNegotiationDao,
        packetNegotiationMapper: PacketNegotiationMapper
    ): PacketNegotiationRepository {
        return PacketNegotiationRepositoryImpl(
            functionsApi,
            packetNegotiationDao,
            packetNegotiationMapper
        )
    }

    @PerScreen
    @Provides
    fun providePacketNegotiationDao(appDatabase: AppDatabase): PacketNegotiationDao {
        return appDatabase.packetNegotiationDao()
    }

    @PerScreen
    @Provides
    fun provideFunctionApi(retrofit: Retrofit): FunctionsApi {
        return retrofit.create(FunctionsApi::class.java)
    }

    @PerScreen
    @Provides
    fun providePacketNegotiationMapper(): PacketNegotiationMapper {
        return PacketNegotiationMapper()
    }
}