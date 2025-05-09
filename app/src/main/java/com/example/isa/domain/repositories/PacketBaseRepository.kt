package com.example.isa.domain.repositories

import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.display.DisplayPacketFunction
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import io.reactivex.Single

interface PacketBaseRepository {

    fun loadPacketBase(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String,
        task: String
    ): Single<List<DisplayPacketTask>>

    fun loadPacketFunction(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String,
        idTask: Int
    ): Single<List<DisplayPacketFunction>>

    fun getTaskById(id: Int): Single<PacketTask>
}