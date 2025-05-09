package com.example.isa.domain.entity.local.display

data class DisplayPacketTask(
    val id: Int,
    val taskId: Int,
    val docIncoming: String,
    val nameTask: String,
    val dateTaskTr: String,
    val clientDevap: String,
    val docRegistryName: String,
    val curator: String,
    val dev: String,
    val tst: String,
    val nameStateTask: String,
    val committer: String,
    val memoTask: String
)