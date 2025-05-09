package com.example.isa.domain.repositories

import com.example.isa.domain.entity.local.database.PacketContent
import com.example.isa.domain.entity.local.display.DisplayPacketcontent
import io.reactivex.Completable
import io.reactivex.Single

interface PacketContentRepository {

    fun loadPacketContent(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String,
        packId: Int
    ): Single<List<DisplayPacketcontent>>
}