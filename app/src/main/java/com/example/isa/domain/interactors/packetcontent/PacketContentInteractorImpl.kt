package com.example.isa.domain.interactors.packetcontent

import com.example.isa.common.constants.NetworkConstants
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.domain.entity.local.database.PacketContent
import com.example.isa.domain.entity.local.display.DisplayPacketcontent
import com.example.isa.domain.repositories.PacketContentRepository
import com.example.isa.domain.repositories.PreferencesRepository
import io.reactivex.Completable
import io.reactivex.Single

class PacketContentInteractorImpl(
    private val packet: PacketContentRepository,
    private val repository: PreferencesRepository,
    private val schedulers: SchedulersProvider
) : PacketContentInteractor {

    override fun loadPacket(id: Int, packId: Int): Single<List<DisplayPacketcontent>> {
        val sessionId = repository.functionSessionId
        val appCode = NetworkConstants.APP_PACKET_NEGOTIATION_CODE
        val appVersion = repository.versionName

        return packet.loadPacketContent(sessionId, id, appCode, appVersion, packId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    }
}