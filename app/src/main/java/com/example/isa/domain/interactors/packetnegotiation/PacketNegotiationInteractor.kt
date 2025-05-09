package com.example.isa.domain.interactors.packetnegotiation

import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import io.reactivex.Completable
import io.reactivex.Single

interface PacketNegotiationInteractor {

    fun loadPacketNegotiation(id: Int): Completable

    fun getPackets(): Single<List<DisplayPacketNegotiation>>

    fun changeSigned(
        id: Int,
        sboName: String,
        packId: String,
        urcCurator: String,
        urcSignTime: String
    ): Completable

    fun getPacketById(packetId: Int): Single<PacketNegotiation>
}