package com.example.isa.presentation.fragment.packetnegotiation.taskpacket.infotaskpacket

import com.arellomobile.mvp.InjectViewState
import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import com.example.isa.domain.interactors.packettask.PacketBaseInteractor
import com.example.isa.presentation.mvp.BasePresenter

@InjectViewState
class PacketTaskDetailPresenter(private val interactor: PacketBaseInteractor) :
    BasePresenter<PacketTaskDetailView>() {

    private var task: PacketTask? = null

    fun getPacketTask(id: Int) {
        viewState.showProgress()
        subscription.add(
            interactor.getTaskById(id)
                .subscribe({ task ->
                    this.task = task
                    viewState.hideProgress()
                    viewState.showTaskDetails(task)
                }, { error ->
                    viewState.hideProgress()
                })
        )
    }

    fun onTaskDetail() {
        viewState.navigationToFunction(task!!.taskId)
    }
}