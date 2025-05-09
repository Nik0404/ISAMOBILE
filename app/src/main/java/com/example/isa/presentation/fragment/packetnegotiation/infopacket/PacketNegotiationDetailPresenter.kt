package com.example.isa.presentation.fragment.packetnegotiation.infopacket

import com.arellomobile.mvp.InjectViewState
import com.example.isa.domain.interactors.packetnegotiation.PacketNegotiationInteractor
import com.example.isa.presentation.fragment.packetnegotiation.PacketNegotiationView
import com.example.isa.presentation.fragment.packetnegotiation.base.PacketNegotiationBasePresenter
import com.example.isa.presentation.mvp.BasePresenter

@InjectViewState
class PacketNegotiationDetailPresenter(
    interactor: PacketNegotiationInteractor
) : PacketNegotiationBasePresenter<PacketNegotiationDetailView>(interactor) {

    private var currentId: Int = -1

    fun loadPacketNegotiationDetail(id: Int) {
        currentId = id
        viewState.showProgress()
        subscription.add(
            interactor.getPacketById(id)
                .subscribe({ packet ->
                    viewState.hideProgress()
                    viewState.showPacketDetails(packet)
                }, { error ->
                    viewState.hideProgress()
                })
        )
    }

    override fun onSignedSuccess() {
        if (currentId != -1) {
            loadPacketNegotiationDetail(currentId)
        }
    }
}