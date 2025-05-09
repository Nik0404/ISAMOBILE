package com.example.isa.di.fragment.packetnegotiation

import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.packetnegotiation.PacketNegotiationRepositoryModule
import com.example.isa.domain.interactors.packetcontent.PacketContentInteractor
import com.example.isa.domain.interactors.packetcontent.PacketContentInteractorImpl
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractor
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractorImpl
import com.example.isa.domain.repositories.PacketContentRepository
import com.example.isa.domain.repositories.PacketNegotiationRepository
import com.example.isa.domain.repositories.PreferencesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [PacketNegotiationRepositoryModule::class])
class PacketModule {

    @Provides
    @PerScreen
    fun providePacketNegotiationInteractor(
        packetNegotiationRepository: PacketNegotiationRepository,
        preferencesRepository: PreferencesRepository,
        schedulersProvider: SchedulersProvider
    ): PacketNegotiationInteractor {
        return PacketNegotiationInteractorImpl(
            packetNegotiationRepository,
            preferencesRepository,
            schedulersProvider
        )
    }
}