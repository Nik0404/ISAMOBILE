package com.example.isa.di.fragment.packettask.task

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.PacketBaseFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [PacketTaskModule::class])
interface PacketTaskComponent : BaseComponent<PacketBaseFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<PacketTaskComponent, PacketTaskModule>
}