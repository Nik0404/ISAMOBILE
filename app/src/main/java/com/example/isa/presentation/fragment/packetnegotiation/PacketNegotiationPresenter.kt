package com.example.isa.presentation.fragment.packetnegotiation

import com.arellomobile.mvp.InjectViewState
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractor
import com.example.isa.presentation.fragment.packetnegotiation.base.PacketNegotiationBasePresenter

@InjectViewState
class PacketNegotiationPresenter(
    interactor: PacketNegotiationInteractor
) : PacketNegotiationBasePresenter<PacketNegotiationView>(interactor) {

    private var packets: List<DisplayPacketNegotiation> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadDataFromDbOrNetwork()
    }

    private fun loadDataFromDbOrNetwork() {
        viewState.showProgress()
        subscription.add(
            interactor.getPackets()
                .doFinally { viewState.hideProgress() }
                .subscribe({
                    packets = it
                    viewState.showPackets(it)
                }) { error ->
                    viewState.showError(error.message)
                }
        )
    }

    private fun fetchPacketsFromServer() {
        viewState.showProgress()
        subscription.add(
            interactor.loadPacketNegotiation(id)
                .andThen(interactor.getPackets())
                .subscribe({ newPackets ->
                    packets = newPackets
                    viewState.hideProgress()
                    viewState.hideRefreshPacket()
                    viewState.showPackets(newPackets)
                }) { error ->
                    viewState.hideProgress()
                    viewState.showError(error.message)
                }
        )
    }

    override fun onSignedSuccess() = loadDataFromDbOrNetwork()

    fun updateUI() = loadDataFromDbOrNetwork()

    fun refreshPacketNegotiation() {
        viewState.showRefreshPacket()
        fetchPacketsFromServer()
    }

    fun onInfoPacketNegotiation(position: Int) =
        viewState.navigateToPacketDetails(packets[position])
}