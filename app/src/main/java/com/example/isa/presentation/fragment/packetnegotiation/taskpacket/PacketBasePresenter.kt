package com.example.isa.presentation.fragment.packetnegotiation.taskpacket

import com.arellomobile.mvp.InjectViewState
import com.example.isa.R
import com.example.isa.common.constants.FunctionsConstants.PACKAGE_RECONCILIATION
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.presentation.mvp.BasePresenter

@InjectViewState
class PacketBasePresenter(
    private val interactor: PacketBaseInteractor
) : BasePresenter<PacketBaseView>() {

    private var packets: List<DisplayPacketTask> = emptyList()

    fun loadTask(id: String) {
        viewState.showProgress()
        subscription.add(
            interactor.loadPacket(PACKAGE_RECONCILIATION, id)
                .subscribe({
                    packets = it
                    viewState.hideProgress()
                    viewState.showTask(it)
                }, {
                    viewState.showError(R.string.data_loading_error_message)
                    viewState.hideProgress()
                })
        )
    }

    fun onInfoPacketTask(position: Int) = viewState.navigateToPacketTaskDetails(packets[position])
}