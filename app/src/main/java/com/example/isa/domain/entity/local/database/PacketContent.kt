package com.example.isa.domain.entity.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "packet_content")
data class PacketContent(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "pack_id") val packId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "component_type") val componentType: String,
    @ColumnInfo(name = "version") val version: Int,
    @ColumnInfo(name = "subsystem") val subsystem: String,
    @ColumnInfo(name = "developer") val developer: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "dt_public") val dtPublic: String,
) : AcronEntity
