package com.example.isa.di.fragment.packetnegotiation.packetnegotiation

import com.example.isa.di.fragment.packetnegotiation.PacketModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractor
import com.example.isa.presentation.adapter.packetnegotiation.PacketNegotiationAdapter
import com.example.isa.presentation.fragment.packetnegotiation.PacketNegotiationPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [PacketModule::class])
class PacketNegotiationModule : BaseModule {

    @Provides
    @PerScreen
    fun providePresenter(
        packetNegotiation: PacketNegotiationInteractor,
    ): PacketNegotiationPresenter {
        return PacketNegotiationPresenter(packetNegotiation)
    }

    @Provides
    @PerScreen
    fun providePacketNegotiationAdapter(): PacketNegotiationAdapter = PacketNegotiationAdapter()

}