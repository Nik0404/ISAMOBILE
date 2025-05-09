package com.example.isa.data.util.mappers

import com.example.isa.common.extension.toId
import com.example.isa.data.util.constants.PacketFunctionColumns
import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.domain.entity.local.database.AcronEntity
import com.example.isa.domain.entity.local.display.DisplayPacketFunction

class PacketFunctionMapper : EntityMapper<List<String>, AcronEntity> {

    override fun fromSchemaToEntity(schema: List<String>): AcronEntity {
        return DisplayPacketFunction(
            idTask = schema[PacketFunctionColumns.ID_TASK_COLUMN_POSITION].toId(),
            functionId = schema[PacketFunctionColumns.FUNCTION_ID_COLUMN_POSITION].toId(),

            functionIdFnm = schema[PacketFunctionColumns.FUNCTION_ID_FNM_COLUMN_POSITION],
            writeAccess = schema.getOrNull(PacketFunctionColumns.WRITE_ACCESS_COLUMN_POSITION)?.toIntOrNull() ?: 0,
            ready = schema.getOrNull(PacketFunctionColumns.READY_COLUMN_POSITION)?.toIntOrNull() ?: 0,
            scope = schema.getOrNull(PacketFunctionColumns.SCOPE_COLUMN_POSITION)?.toIntOrNull() ?: 0,
            scopeSnm = schema[PacketFunctionColumns.SCOPE_SNM_COLUMN_POSITION],
            subsystem = schema.getOrNull(PacketFunctionColumns.SUBSYSTEM_COLUMN_POSITION)?.toIntOrNull() ?: 0,
            subsystemFnm = schema[PacketFunctionColumns.SUBSYSTEM_FNM_COLUMN_POSITION],
            subsystemSnm = schema[PacketFunctionColumns.SUBSYSTEM_SNM_COLUMN_POSITION],
            dbObjPrefix = schema[PacketFunctionColumns.DB_OBJ_PREFIX_COLUMN_POSITION],

            isIsa2005 = schema[PacketFunctionColumns.IS_ISA_2005_COLUMN_POSITION],
            isIsa2010 = schema[PacketFunctionColumns.IS_ISA_2010_COLUMN_POSITION],
            isIsaWeb = schema[PacketFunctionColumns.IS_ISA_WEB_COLUMN_POSITION],
            isStartIsaWeb = schema[PacketFunctionColumns.IS_START_ISA_WEB_COLUMN_POSITION]
        )
    }
}