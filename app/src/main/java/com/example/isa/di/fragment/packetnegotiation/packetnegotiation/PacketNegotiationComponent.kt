package com.example.isa.di.fragment.packetnegotiation.packetnegotiation

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.packetnegotiation.PacketNegotiationFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [PacketNegotiationModule::class])
interface PacketNegotiationComponent : BaseComponent<PacketNegotiationFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<PacketNegotiationComponent, PacketNegotiationModule>
}