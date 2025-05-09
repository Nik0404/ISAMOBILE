package com.example.isa.presentation.fragment.login.auth

import com.arellomobile.mvp.InjectViewState
import com.example.isa.R
import com.example.isa.common.constants.utils.NetworkErrorsParser
import com.example.isa.domain.interactors.login.LoginInteractor
import com.example.isa.presentation.fragment.login.base.BaseLoginPresenter

@InjectViewState
class LoginPresenter(
    private val interactor: LoginInteractor,
    networkErrorsParser: NetworkErrorsParser
) : BaseLoginPresenter<LoginView>(interactor, networkErrorsParser) {

    var isAfterLogout = false

    fun getDeviceId(): String = interactor.getDeviceId()

    fun setDeviceId(deviceId: String) = interactor.setDeviceId(deviceId)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (isAfterLogout) {
            login()
        } else checkSessionId()
    }

    private fun login() {
        val login = interactor.getLogin()
        val password = interactor.getPassword()

        loginUser(login, password)
    }

    private fun checkSessionId() {
        val sessionId = interactor.getSessionId()

        if (sessionId.isNotEmpty()) {
            viewState.openMainScreen()
        }
    }

    private fun loginUser(login: String, password: String) {
        viewState.showProgress()
        subscription.add(
            interactor
                .loginUser(login, password)
                .subscribe({ userFunctions ->
                    viewState.hideKeyboard()
                    viewState.hideProgress()
                    viewState.openMainScreen()
                }, { error ->
                    viewState.hideProgress()
                    handleError(error)
                })
        )
    }

    fun onLoginButtonClicked(login: String, password: String) {
        if (isLoginValid(login) && isPasswordValid(password)) {
            loginUser(login, password)
        } else viewState.showSnackbar(R.string.login_fields_empty)
    }

    private fun isLoginValid(login: String): Boolean {
        return login.isNotEmpty()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}