package com.example.isa.di.fragment.packettask.task

import com.example.isa.di.fragment.packettask.PacketModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.presentation.adapter.packettask.PacketTaskAdapter
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.PacketBasePresenter
import dagger.Module
import dagger.Provides

@Module(includes = [PacketModule::class])
class PacketTaskModule : BaseModule {

    @Provides
    @PerScreen
    fun providePresenter(interactor: PacketBaseInteractor): PacketBasePresenter {
        return PacketBasePresenter(interactor)
    }

    @Provides
    @PerScreen
    fun provideTaskAdapter(): PacketTaskAdapter = PacketTaskAdapter()
}