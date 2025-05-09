package com.example.isa.presentation.fragment.packetnegotiation.taskpacket.infotaskpacket

import ccom.example.isa.presentation.mvp.BaseView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.database.PacketTask

interface PacketTaskDetailView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTaskDetails(task: PacketTask)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigationToFunction(id: Int)
}