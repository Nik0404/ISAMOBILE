package com.example.isa.domain.entity.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "packet_negotiation")
data class PacketNegotiation(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "pack_id") val packId: Int,
    @ColumnInfo(name = "task_list") val taskList: String,
    @ColumnInfo(name = "blocking_task_list") val blockingTaskList: String,
    @ColumnInfo(name = "pack_description") val packDescription: String,
    @ColumnInfo(name = "pack_name") val packName: String,
    @ColumnInfo(name = "ts_create") val tsCreate: String,
    @ColumnInfo(name = "ts_send") val tsSend: String,
    @ColumnInfo(name = "urc_dev") val urcDev: Int,
    @ColumnInfo(name = "urc_dev_fnm") val urcDevFNM: String,
    @ColumnInfo(name = "urc_dev_snm") val urcDevSNM: String,
    @ColumnInfo(name = "urc_lead_dev") val urcLeadDev: Int,
    @ColumnInfo(name = "urc_lead_dev_fnm") val urcLeadDevFNM: String,
    @ColumnInfo(name = "urc_lead_dev_snm") val urcLeadDevSNM: String,
    @ColumnInfo(name = "urc_sign_exec") val urcSignExec: Int,
    @ColumnInfo(name = "urc_sign_exec_fnm") val urcSignExecFNM: String,
    @ColumnInfo(name = "urc_sign_exec_snm") val urcSignExecSNM: String,
    @ColumnInfo(name = "urc_sign_time") val urcSignTime: String,
    @ColumnInfo(name = "is_signed") val isSigned: Int,
    @ColumnInfo(name = "main_urc_curator") val mainUrcCurator: Int,
    @ColumnInfo(name = "urc_curator") val urcCurator: Int,
    @ColumnInfo(name = "max_pack_change") val maxPackChange: String,
    @ColumnInfo(name = "max_pack_change_snm") val maxPackChangeSnm: String
) : AcronEntity {
    @Ignore
    var attachmentsCount: Int = 0
}