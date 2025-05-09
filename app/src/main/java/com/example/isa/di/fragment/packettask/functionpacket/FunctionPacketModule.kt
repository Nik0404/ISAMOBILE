package com.example.isa.di.fragment.packettask.functionpacket

import com.example.isa.di.fragment.packettask.PacketModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.presentation.adapter.packetfunction.FunctionPacketAdapter
import com.example.isa.presentation.adapter.packettask.PacketTaskAdapter
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.functionpacket.FunctionPacketPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [PacketModule::class])
class FunctionPacketModule : BaseModule {

    @Provides
    @PerScreen
    fun providePresenter(interactor: PacketBaseInteractor): FunctionPacketPresenter =
        FunctionPacketPresenter(interactor)

    @Provides
    @PerScreen
    fun provideFunctionPacketAdapter(): FunctionPacketAdapter = FunctionPacketAdapter()

}