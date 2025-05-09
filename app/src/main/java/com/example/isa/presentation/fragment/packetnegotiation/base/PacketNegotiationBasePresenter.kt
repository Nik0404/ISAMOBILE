package com.example.isa.presentation.fragment.packetnegotiation.base

import com.example.isa.common.constants.FunctionsConstants
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractor
import com.example.isa.presentation.mvp.BasePresenter

abstract class PacketNegotiationBasePresenter<ViewState : PacketNegotiationBaseView>(
    protected val interactor: PacketNegotiationInteractor
) : BasePresenter<ViewState>() {

    protected val id = FunctionsConstants.PACKAGE_RECONCILIATION

    fun updatePacketSigned(
        functionId: Int,
        sboName: String,
        packId: String,
        urcCurator: String,
        urcSignTime: String
    ) {
        viewState.showProgress()
        subscription.add(
            interactor.changeSigned(functionId, sboName, packId, urcCurator, urcSignTime)
                .subscribe({
                    viewState.hideProgress()
                    onSignedSuccess()
                }, { error ->
                    viewState.hideProgress()
                })
        )
    }

    protected open fun onSignedSuccess() {}
}