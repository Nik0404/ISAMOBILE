package com.example.isa.domain.interactors.packettask

import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.display.DisplayPacketFunction
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import io.reactivex.Single

interface PacketBaseInteractor {

    fun loadPacket(id: Int, task: String): Single<List<DisplayPacketTask>>

    fun loadFunction(id: Int, taskId: Int): Single<List<DisplayPacketFunction>>

    fun getTaskById(id: Int): Single<PacketTask>
}