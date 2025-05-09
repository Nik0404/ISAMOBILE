package com.example.isa.data.util.mappers

import com.example.isa.common.extension.toId
import com.example.isa.data.util.constants.PacketContentColumns
import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.domain.entity.local.database.AcronEntity
import com.example.isa.domain.entity.local.database.PacketContent

class PacketContentMapper : EntityMapper<List<String>, AcronEntity> {

    override fun fromSchemaToEntity(schema: List<String>): AcronEntity {
        val packet = PacketContent(
            id = schema[PacketContentColumns.PACK_ID_COLUMN_POSITION].toId(),
            packId = schema[PacketContentColumns.PACK_ID_COLUMN_POSITION].toId(),
            name = schema[PacketContentColumns.NAME_COLUMN_POSITION],
            title = schema[PacketContentColumns.TITLE_COLUMN_POSITION],
            componentType = schema[PacketContentColumns.COMPONENT_TYPE_SNM_COLUMN_POSITION],
            version = schema[PacketContentColumns.VERSION_COLUMN_POSITION].toId(),
            subsystem = schema[PacketContentColumns.SUBSYSTEM_COLUMN_POSITION],
            developer = schema[PacketContentColumns.DEVELOPER_TN_SNM_COLUMN_POSITION],
            status = schema[PacketContentColumns.STATUS_SNM_COLUMN_POSITION],
            dtPublic = schema[PacketContentColumns.DT_PUBLIC_COLUMN_POSITION],
        )
        return packet
    }
}