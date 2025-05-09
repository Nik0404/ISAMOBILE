package com.example.isa.presentation.fragment.packetnegotiation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import com.example.isa.presentation.fragment.packetnegotiation.base.PacketNegotiationBaseView

interface PacketNegotiationView : PacketNegotiationBaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPackets(packets: List<DisplayPacketNegotiation>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideRefreshPacket()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showRefreshPacket()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun notifyItemChanged(index: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(message: String?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToPacketDetails(packet: DisplayPacketNegotiation)
}