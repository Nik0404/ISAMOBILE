package com.example.isa.domain.interactors.packettask

import com.example.isa.common.constants.NetworkConstants
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.display.DisplayPacketFunction
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import com.example.isa.domain.repositories.PacketBaseRepository
import com.example.isa.domain.repositories.PreferencesRepository
import io.reactivex.Single

class PacketBaseInteractorImpl(
    private val packet: PacketBaseRepository,
    private val repository: PreferencesRepository,
    private val scheduler: SchedulersProvider
) : PacketBaseInteractor {

    override fun loadPacket(id: Int, task: String): Single<List<DisplayPacketTask>> {
        val sessionId = repository.functionSessionId
        val appCode = NetworkConstants.APP_PACKET_NEGOTIATION_CODE
        val appVersion = repository.versionName

        return packet.loadPacketBase(sessionId, id, appCode, appVersion, task)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun loadFunction(id: Int, taskId: Int): Single<List<DisplayPacketFunction>> {
        val sessionId = repository.functionSessionId
        val appCode = NetworkConstants.APP_PACKET_NEGOTIATION_CODE
        val appVersion = repository.versionName

        return packet.loadPacketFunction(sessionId, id, appCode, appVersion, taskId)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

    override fun getTaskById(id: Int): Single<PacketTask> {
        return packet.getTaskById(id)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }
}