package com.example.isa.presentation.activity.main

import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import ccom.example.isa.presentation.mvp.BaseView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.isa.domain.entity.local.database.User

interface MainView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun lockDrawer()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun unlockDrawer()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDrawer()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeDrawer()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setUser(user: User)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSnackbar(resourceId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSnackbar(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openLoginActivity()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setMenuResource(@MenuRes menuResourceId: Int, @IdRes checkedItemId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setupNavController(@NavigationRes navigationGraphId: Int)

}