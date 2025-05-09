package com.example.isa.domain.entity.local.display

data class DisplayPacketNegotiation(
    val id: Int,
    val packId: Int,
    val taskList: String,
    val blockingTaskName: String,
    val packDescription: String,
    val packName: String,
    val tsCreate: String,
    val tsSend: String,
    val urcDev: String,
    val urcLeadDev: String,
    val urcSignExec: String,
    val urcSignTime: String,
    val isSigned: Int,
    val manUrcCurator: Int,
    val urcCurator: Int,
    val maxPackChange: String,
    val maxPackChangeSnm: String
)