package com.example.isa.presentation.fragment.login.auth

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.presentation.fragment.login.base.BaseLoginView

interface LoginView : BaseLoginView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideKeyboard()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setDeviceId(deviceId: String)
}