package com.example.isa.domain.interactors.packetcontent

import com.example.isa.domain.entity.local.display.DisplayPacketcontent
import io.reactivex.Single

interface PacketContentInteractor {

    fun loadPacket(id: Int, packId: Int): Single<List<DisplayPacketcontent>>
}