package com.example.isa.di.fragment.packetcontent

import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.di.fragment.packetnegotiation.PacketModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.packetcontent.PacketContentRepositoryModule
import com.example.isa.domain.interactors.packetcontent.PacketContentInteractor
import com.example.isa.domain.interactors.packetcontent.PacketContentInteractorImpl
import com.example.isa.domain.repositories.PacketContentRepository
import com.example.isa.domain.repositories.PreferencesRepository
import com.example.isa.presentation.adapter.packetcontent.PacketContentAdapter
import com.example.isa.presentation.fragment.packetnegotiation.contentpacket.PacketContentPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [PacketContentRepositoryModule::class])
class PacketContentModule : BaseModule {

    @Provides
    @PerScreen
    fun providePresenter(interactor: PacketContentInteractor): PacketContentPresenter {
        return PacketContentPresenter(interactor)
    }

    @Provides
    @PerScreen
    fun providePacketContentInteractor(
        packetContentRepository: PacketContentRepository,
        preferencesRepository: PreferencesRepository,
        schedulersProvider: SchedulersProvider
    ): PacketContentInteractor {
        return PacketContentInteractorImpl(
            packetContentRepository,
            preferencesRepository,
            schedulersProvider
        )
    }

    @Provides
    @PerScreen
    fun providePacketContentAdapter(): PacketContentAdapter = PacketContentAdapter()
}