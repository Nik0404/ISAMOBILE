package com.example.isa.di.repository.packetcontent

import com.example.isa.data.datasource.database.AppDatabase
import com.example.isa.data.datasource.database.dao.PacketContentDao
import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.repositories.PacketContentRepositoryImpl
import com.example.isa.data.util.mappers.PacketContentMapper
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.repositories.PacketContentRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PacketContentRepositoryModule {

    @PerScreen
    @Provides
    fun providePacketContentRepository(
        functionsApi: FunctionsApi,
        packetContentDao: PacketContentDao,
        packetContentMapper: PacketContentMapper
    ): PacketContentRepository {
        return PacketContentRepositoryImpl(
            functionsApi,
            packetContentDao,
            packetContentMapper
        )
    }

    @PerScreen
    @Provides
    fun providePacketContentDao(appDatabase: AppDatabase): PacketContentDao {
        return appDatabase.packetContentDao()
    }

    @PerScreen
    @Provides
    fun provideFunctionApi(retrofit: Retrofit): FunctionsApi {
        return retrofit.create(FunctionsApi::class.java)
    }

    @PerScreen
    @Provides
    fun providePacketContentMapper(): PacketContentMapper {
        return PacketContentMapper()
    }
}