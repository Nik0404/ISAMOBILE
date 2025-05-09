package com.example.isa.presentation.activity.main

import com.arellomobile.mvp.InjectViewState
import com.example.isa.R
import com.example.isa.common.constants.utils.NetworkErrorsParser
import com.example.isa.domain.interactors.main.MainInteractor
import com.example.isa.presentation.mvp.BasePresenter
import timber.log.Timber

@InjectViewState
class MainPresenter(
    private val mainInteractor: MainInteractor,
    private val networkErrorsParser: NetworkErrorsParser
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        prepareDrawerLayoutMenu()

        loadCurrentUser()
    }

    private fun prepareDrawerLayoutMenu() {
        viewState.setMenuResource(R.menu.menu_isa, R.id.myPageFragment)
        viewState.setupNavController(R.navigation.mobile_navigation)
    }

    private fun loadCurrentUser() {
        subscription.add(
            mainInteractor.getCurrentUser()
                .subscribe({
                    viewState.setUser(it)
                }, {
                    viewState.showSnackbar(R.string.user_loading_error_message)
                    Timber.e(it)
                })
        )
    }

    fun logout() {
        viewState.closeDrawer()
        subscription.add(
            mainInteractor.logout()
                .subscribe({
                    viewState.openLoginActivity()
                }, {
                    viewState.openLoginActivity()

                    Timber.e(it)
                })
        )
    }

    fun refreshSessionId() {
        subscription.add(
            mainInteractor.refreshSessionId()
                .subscribe({}, { Timber.e(it) })
        )
    }

}