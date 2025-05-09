package com.example.isa.di.fragment.packettask

import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.packettask.PacketTaskRepositoryModule
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.domain.interactors.packettask.PacketBaseInteractorImpl
import com.example.isa.domain.repositories.PacketBaseRepository
import com.example.isa.domain.repositories.PreferencesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [PacketTaskRepositoryModule::class])
class PacketModule {

    @Provides
    @PerScreen
    fun providePacketBaseInteractor(
        packetBaseRepository: PacketBaseRepository,
        preferencesRepository: PreferencesRepository,
        schedulersProvider: SchedulersProvider
    ): PacketBaseInteractor {
        return PacketBaseInteractorImpl(
            packetBaseRepository,
            preferencesRepository,
            schedulersProvider
        )
    }
}