package com.example.isa.di.fragment.packettask.functionpacket

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.functionpacket.FunctionPacketFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [FunctionPacketModule::class])
interface FunctionPacketComponent : BaseComponent<FunctionPacketFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<FunctionPacketComponent, FunctionPacketModule>
}