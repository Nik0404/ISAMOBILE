package com.example.isa.di.fragment.packetcontent

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.packetnegotiation.contentpacket.PacketContentFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [PacketContentModule::class])
interface PacketContentComponent : BaseComponent<PacketContentFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<PacketContentComponent, PacketContentModule>
}