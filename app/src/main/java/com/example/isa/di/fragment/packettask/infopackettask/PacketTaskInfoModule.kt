package com.example.isa.di.fragment.packettask.infopackettask

import com.example.isa.di.fragment.packettask.PacketModule
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.infotaskpacket.PacketTaskDetailPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [PacketModule::class])
class PacketTaskInfoModule : BaseModule {

    @Provides
    @PerScreen
    fun providePresenter(interactor: PacketBaseInteractor): PacketTaskDetailPresenter {
        return PacketTaskDetailPresenter(interactor)
    }
}