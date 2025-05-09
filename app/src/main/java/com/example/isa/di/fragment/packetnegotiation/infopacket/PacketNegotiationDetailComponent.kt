package com.example.isa.di.fragment.packetnegotiation.infopacket

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.packetnegotiation.infopacket.PacketNegotiationDetailFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [PacketNegotiationDetailModule::class])
interface PacketNegotiationDetailComponent : BaseComponent<PacketNegotiationDetailFragment> {

    @Subcomponent.Builder
    interface Builder :
        BaseComponentBuilder<PacketNegotiationDetailComponent, PacketNegotiationDetailModule>
}