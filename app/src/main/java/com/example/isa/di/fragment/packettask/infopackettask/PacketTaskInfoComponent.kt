package com.example.isa.di.fragment.packettask.infopackettask

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.infotaskpacket.PacketTaskDetailFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [PacketTaskInfoModule::class])
interface PacketTaskInfoComponent : BaseComponent<PacketTaskDetailFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<PacketTaskInfoComponent, PacketTaskInfoModule>
}