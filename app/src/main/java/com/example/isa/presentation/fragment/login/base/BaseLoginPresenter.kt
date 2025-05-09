package com.example.isa.presentation.fragment.login.base

import com.example.isa.R
import com.example.isa.common.constants.NetworkConstants
import com.example.isa.common.constants.utils.NetworkErrorsParser
import com.example.isa.domain.interactors.login.LoginInteractor
import com.example.isa.presentation.mvp.BasePresenter
import retrofit2.HttpException
import timber.log.Timber

abstract class BaseLoginPresenter<V : BaseLoginView>(
    private val interactor: LoginInteractor,
    private val networkErrorParses: NetworkErrorsParser
) : BasePresenter<V>() {

    private var updateApplicationUrl = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val versionName = interactor.getAppVersionName()
        viewState.setAppVersionName(versionName)
    }

    protected fun getDivisionsFromServer() {
        viewState.showProgress()
    }

    protected fun handleError(throwable: Throwable) {
        Timber.e(throwable)

        if (throwable is HttpException) {
            val jsonError = throwable.response()?.errorBody()?.string()!!

            val errorCode = networkErrorParses.parseErrorCode(jsonError)
            val errorMwssage = networkErrorParses.parseErrorMessage(jsonError)

            when {
                errorCode == NetworkConstants.APP_VERSION_ERROR_CODE -> {
                    updateApplicationUrl = networkErrorParses.parseErrorUrl(jsonError)
                    viewState.showSnackbar(errorMwssage, R.string.go_to)
                }

                errorCode == NetworkConstants.OUT_OF_DATE_PASSWORD_ERROR_CODE -> {
                    viewState.showSnackbar(R.string.password_out_of_date)
                }

                errorMwssage.isNotEmpty() -> {
                    viewState.showSnackbar(errorMwssage)
                }

                else -> viewState.showSnackbar(R.string.error_message)
            }
        } else viewState.showSnackbar(R.string.error_message)
    }

    fun snackbarActionClicked() = viewState.openWebsite(updateApplicationUrl)
}