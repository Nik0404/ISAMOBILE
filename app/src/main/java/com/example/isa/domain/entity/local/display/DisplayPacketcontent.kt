package com.example.isa.domain.entity.local.display

data class DisplayPacketcontent(
    val id: Int,
    val packId: Int,
    val name: String,
    val componentType: String,
    val title: String,
    val version: Int,
    val subsystem: String,
    val developer: String,
    val status: String,
    val dtPublic: String
)