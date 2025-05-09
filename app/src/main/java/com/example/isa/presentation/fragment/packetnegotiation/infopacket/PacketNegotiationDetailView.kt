package com.example.isa.presentation.fragment.packetnegotiation.infopacket

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.presentation.fragment.packetnegotiation.base.PacketNegotiationBaseView

interface PacketNegotiationDetailView : PacketNegotiationBaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPacketDetails(packet: PacketNegotiation)
}