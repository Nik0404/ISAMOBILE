package com.example.isa.presentation.fragment.packetnegotiation.taskpacket.functionpacket

import ccom.example.isa.presentation.mvp.BaseView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.display.DisplayPacketFunction

interface FunctionPacketView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFunction(function: List<DisplayPacketFunction>)
}