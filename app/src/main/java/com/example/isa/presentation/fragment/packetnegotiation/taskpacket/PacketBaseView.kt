package com.example.isa.presentation.fragment.packetnegotiation.taskpacket

import ccom.example.isa.presentation.mvp.BaseView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import com.example.isa.domain.entity.local.display.DisplayPacketTask

interface PacketBaseView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTask(task: List<DisplayPacketTask>)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToPacketTaskDetails(packet: DisplayPacketTask)
}