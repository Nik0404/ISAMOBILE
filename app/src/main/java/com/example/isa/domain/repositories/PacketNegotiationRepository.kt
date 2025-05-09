package com.example.isa.domain.repositories

import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import io.reactivex.Completable
import io.reactivex.Single

interface PacketNegotiationRepository {

    fun loadPacketNegotiation(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String
    ): Completable

    fun getPacketNegotiation(): Single<List<DisplayPacketNegotiation>>

    fun changeSigned(
        sessionId: String,
        id: Int,
        sboName: String,
        packId: String,
        urcCurator: String,
        urcSignTime: String
    ): Completable

    fun getPacketNegotiationId(packetId: Int): Single<PacketNegotiation>
}