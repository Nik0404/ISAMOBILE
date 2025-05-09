package com.example.isa.presentation.fragment.packetnegotiation.contentpacket

import ccom.example.isa.presentation.mvp.BaseView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.display.DisplayPacketcontent

interface PacketContentView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPacketContent(packetContent: List<DisplayPacketcontent>)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)
}