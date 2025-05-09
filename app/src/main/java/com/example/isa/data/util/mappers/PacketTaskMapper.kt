package com.example.isa.data.util.mappers

import com.example.isa.common.extension.toId
import com.example.isa.data.util.constants.PacketTaskColumns
import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.domain.entity.local.database.AcronEntity
import com.example.isa.domain.entity.local.database.PacketTask

class PacketTaskMapper : EntityMapper<List<String>, AcronEntity> {

    override fun fromSchemaToEntity(schema: List<String>): AcronEntity {
        return PacketTask(
            id = schema[PacketTaskColumns.TASK_ID_COLUMN_POSITION].toId(),
            taskId = schema[PacketTaskColumns.TASK_ID_COLUMN_POSITION].toId(),
            docIncoming = schema[PacketTaskColumns.DOC_INCOMING_COLUMN_POSITION],
            nameTask = schema[PacketTaskColumns.NAME_TASK_COLUMN_POSITION],
            dateTaskTr = schema[PacketTaskColumns.DATE_TASK_TR_COLUMN_POSITION],
            clientDevap = schema[PacketTaskColumns.CLIENT_DEVAP_NAME_COLUMN_POSITION],
            docRegistryName = schema[PacketTaskColumns.DOC_REGISTRY_NAME_COLUMN_POSITION],
            curator = schema[PacketTaskColumns.CURATOR_NAME_COLUMN_POSITION],
            dev = schema[PacketTaskColumns.DEV_NAME_COLUMN_POSITION],
            tst = schema[PacketTaskColumns.TST_NAME_COLUMN_POSITION],
            nameStateTask = schema[PacketTaskColumns.NAME_STATE_TASK_POSITION],
            committer = schema[PacketTaskColumns.DOC_SENDER_COMMITTER_COLUMN_POSITION],
            memoTask = schema[PacketTaskColumns.MEMO_TASK_COLUMN_POSITION]
        )
    }
}