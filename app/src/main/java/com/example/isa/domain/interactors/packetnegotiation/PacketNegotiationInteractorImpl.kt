package com.example.isa.domain.interactors.packetnegotiation

import com.example.isa.common.constants.NetworkConstants
import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import com.example.isa.domain.repositories.PacketNegotiationRepository
import com.example.isa.domain.repositories.PreferencesRepository
import io.reactivex.Completable
import io.reactivex.Single

class PacketNegotiationInteractorImpl(
    private val packetNegotiationRepository: PacketNegotiationRepository,
    private val preferencesRepository: PreferencesRepository,
    private val schedulersProvider: SchedulersProvider
) : PacketNegotiationInteractor {

    override fun loadPacketNegotiation(id: Int): Completable {
        val sessionId = preferencesRepository.functionSessionId
        val appCode = NetworkConstants.APP_PACKET_NEGOTIATION_CODE
        val appVersion = preferencesRepository.versionName

        return packetNegotiationRepository.loadPacketNegotiation(sessionId, id, appCode, appVersion)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }

    override fun getPackets(): Single<List<DisplayPacketNegotiation>> {
        return packetNegotiationRepository.getPacketNegotiation()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }

    override fun changeSigned(
        id: Int,
        sboName: String,
        packId: String,
        urcCurator: String,
        urcSignTime: String
    ): Completable {
        val sessionId = preferencesRepository.functionSessionId
        return packetNegotiationRepository.changeSigned(
            sessionId,
            id,
            sboName,
            packId,
            urcCurator,
            urcSignTime
        )
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }

    override fun getPacketById(packetId: Int): Single<PacketNegotiation> {
        return packetNegotiationRepository.getPacketNegotiationId(packetId)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }
}