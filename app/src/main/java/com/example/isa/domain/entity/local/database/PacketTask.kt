package com.example.isa.domain.entity.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "packet_task")
data class PacketTask(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "task_id") val taskId: Int,
    @ColumnInfo(name = "doc_incoming") val docIncoming: String,
    @ColumnInfo(name = "name_task") val nameTask: String,
    @ColumnInfo(name = "date_task_tr") val dateTaskTr: String,
    @ColumnInfo(name = "client_devap_name") val clientDevap: String,
    @ColumnInfo(name = "doc_registry_name") val docRegistryName: String,
    @ColumnInfo(name = "curator_name") val curator: String,
    @ColumnInfo(name = "dev_name") val dev: String,
    @ColumnInfo(name = "tst_name") val tst: String,
    @ColumnInfo(name = "name_state_task") val nameStateTask: String,
    @ColumnInfo(name = "doc_sender_committer") val committer: String,
    @ColumnInfo(name = "memo_task") val memoTask: String
) : AcronEntity
