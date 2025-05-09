package com.example.isa.presentation.fragment.packetnegotiation.contentpacket

import com.arellomobile.mvp.InjectViewState
import com.example.isa.R
import com.example.isa.common.constants.FunctionsConstants.PACKAGE_RECONCILIATION
import com.example.isa.domain.interactors.packetcontent.PacketContentInteractor
import com.example.isa.presentation.mvp.BasePresenter

@InjectViewState
class PacketContentPresenter(
    private val interactor: PacketContentInteractor
) : BasePresenter<PacketContentView>() {

    fun loadPacketContent(packId: Int) {
        viewState.showProgress()
        subscription.add(
            interactor.loadPacket(PACKAGE_RECONCILIATION, packId)
                .subscribe({ response ->
                    viewState.hideProgress()
                    viewState.showPacketContent(response)
                }, {
                    viewState.showError(R.string.data_loading_error_message)
                })
        )
    }
}