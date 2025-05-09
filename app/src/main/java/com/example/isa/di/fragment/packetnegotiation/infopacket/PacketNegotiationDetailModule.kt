package com.example.isa.di.fragment.packetnegotiation.infopacket

import com.example.isa.di.fragment.packetnegotiation.PacketModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractor
import com.example.isa.presentation.fragment.packetnegotiation.infopacket.PacketNegotiationDetailPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [PacketModule::class])
class PacketNegotiationDetailModule : BaseModule {

    @Provides
    @PerScreen
    fun providePresenter(
        packetNegotiation: PacketNegotiationInteractor,
    ): PacketNegotiationDetailPresenter {
        return PacketNegotiationDetailPresenter(packetNegotiation)
    }
}