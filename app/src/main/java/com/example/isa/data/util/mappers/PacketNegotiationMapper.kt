package com.example.isa.data.util.mappers

import com.example.isa.common.extension.toId
import com.example.isa.data.util.constants.PacketNegotiationColumns
import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.domain.entity.local.database.AcronEntity
import com.example.isa.domain.entity.local.database.PacketNegotiation

class PacketNegotiationMapper : EntityMapper<List<String>, AcronEntity> {

    override fun fromSchemaToEntity(schema: List<String>): AcronEntity {
        val packet = PacketNegotiation(
            id = schema[PacketNegotiationColumns.PACK_ID_COLUMN_POSITION].toId(),
            packId = schema[PacketNegotiationColumns.PACK_ID_COLUMN_POSITION].toId(),
            taskList = schema[PacketNegotiationColumns.TASK_LIST_COLUMN_POSITION],
            blockingTaskList = schema[PacketNegotiationColumns.BLOCKING_TASK_LIST_COLUMN_POSITION],
            packDescription = schema[PacketNegotiationColumns.PACK_DESCRIPTION_COLUMN_POSITION],
            packName = schema[PacketNegotiationColumns.PACK_NAME_COLUMN_POSITION],
            tsCreate = schema[PacketNegotiationColumns.TS_CREATE_COLUMN_POSITION],
            tsSend = schema[PacketNegotiationColumns.TS_SEND_COLUMN_POSITION],
            urcDev = schema[PacketNegotiationColumns.URC_DEV_COLUMN_POSITION].toId(),
            urcDevFNM = schema[PacketNegotiationColumns.URC_DEV_FNM_COLUMN_POSITION],
            urcDevSNM = schema[PacketNegotiationColumns.URC_DEV_SNM_COLUMN_POSITION],
            urcLeadDev = schema[PacketNegotiationColumns.URC_LEAD_DEV_COLUMN_POSITION].toId(),
            urcLeadDevFNM = schema[PacketNegotiationColumns.URC_LEAD_DEV_FNM_COLUMN_POSITION],
            urcLeadDevSNM = schema[PacketNegotiationColumns.URC_LEAD_DEV_SNM_COLUMN_POSITION],
            urcSignExec = schema[PacketNegotiationColumns.URC_SIGN_EXEC_COLUMN_POSITION].toId(),
            urcSignExecFNM = schema[PacketNegotiationColumns.URC_SIGN_EXEC_FNM_COLUMN_POSITION],
            urcSignExecSNM = schema[PacketNegotiationColumns.URC_SIGN_EXEC_SNM_COLUMN_POSITION],
            urcSignTime = schema[PacketNegotiationColumns.URC_SIGN_TIME_COLUMN_POSITION],
            isSigned = schema[PacketNegotiationColumns.IS_SIGNED_COLUMN_POSITION].toId(),
            mainUrcCurator = schema[PacketNegotiationColumns.MAIN_URC_CURATOR_COLUMN_POSITION].toId(),
            urcCurator = schema[PacketNegotiationColumns.URC_CURATOR_COLUMN_POSITION].toId(),
            maxPackChange = schema[PacketNegotiationColumns.MAX_PACK_CHANGE_COLUMN_POSITION],
            maxPackChangeSnm = schema[PacketNegotiationColumns.MAX_PACK_CHANGE_SNM_COLUMN_POSITION]
        )

        return packet
    }
}