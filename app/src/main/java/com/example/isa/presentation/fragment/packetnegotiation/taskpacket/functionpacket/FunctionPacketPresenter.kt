package com.example.isa.presentation.fragment.packetnegotiation.taskpacket.functionpacket

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.isa.common.constants.FunctionsConstants.PACKAGE_RECONCILIATION
import com.example.isa.domain.entity.local.display.DisplayPacketFunction
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.presentation.mvp.BasePresenter

@InjectViewState
class FunctionPacketPresenter(
    private val interactor: PacketBaseInteractor
) : BasePresenter<FunctionPacketView>() {

    fun loadTask(id: Int) {
        viewState.showProgress()
        subscription.add(
            interactor.loadFunction(PACKAGE_RECONCILIATION, id)
                .subscribe({
                    viewState.hideProgress()
                    viewState.showFunction(it)
                }, {
                    viewState.hideProgress()
                })
        )
    }

}