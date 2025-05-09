package com.example.isa.data.repositories

import com.example.isa.common.constants.PacketTaskConstants.CLIENT_DEVAPP_NAME
import com.example.isa.common.constants.PacketTaskConstants.CURATOR_KI_FNM
import com.example.isa.common.constants.PacketTaskConstants.DATE_TASK_TR
import com.example.isa.common.constants.PacketTaskConstants.DEV_KI_FNM
import com.example.isa.common.constants.PacketTaskConstants.DOC_INCOMING_NUMBER_FULL
import com.example.isa.common.constants.PacketTaskConstants.DOC_REGISTRY_NAME
import com.example.isa.common.constants.PacketTaskConstants.DOC_SENDER_COMMITTER
import com.example.isa.common.constants.PacketTaskConstants.ID_TASK
import com.example.isa.common.constants.PacketTaskConstants.MEMO_TASK
import com.example.isa.common.constants.PacketTaskConstants.NAME_STATE_TASK
import com.example.isa.common.constants.PacketTaskConstants.NAME_TASK
import com.example.isa.common.constants.PacketTaskConstants.TST_KI_FNM
import com.example.isa.common.constants.PreferencesConstants
import com.example.isa.common.constants.PreferencesConstants.EQUALS_PARAMS
import com.example.isa.common.constants.PreferencesConstants.IN
import com.example.isa.common.constants.SboName.DEV_TASK
import com.example.isa.common.constants.SboName.DEV_TASK_FUNCTION_LIST
import com.example.isa.data.datasource.database.dao.PacketTaskDao
import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.util.mappers.PacketFunctionMapper
import com.example.isa.data.util.mappers.PacketTaskMapper
import com.example.isa.domain.entity.local.database.PacketTask
import com.example.isa.domain.entity.local.display.DisplayPacketFunction
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import com.example.isa.domain.entity.remote.request.GettingDataRequest
import com.example.isa.domain.entity.remote.request.other.Filter
import com.example.isa.domain.repositories.PacketBaseRepository
import io.reactivex.Single

class PacketBaseRepositoryImpl(
    private val api: FunctionsApi,
    private val dao: PacketTaskDao,
    private val mapper: PacketTaskMapper,
    private val packetFunctionMapper: PacketFunctionMapper
) : PacketBaseRepository {

    override fun loadPacketBase(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String,
        task: String
    ): Single<List<DisplayPacketTask>> {
        val filterList = mutableListOf<Filter>().apply {
            add(Filter(PreferencesConstants.ID_TASK, IN, task))
        }

        val reqList = listOf(
            ID_TASK,
            ID_TASK,
            DOC_INCOMING_NUMBER_FULL,
            NAME_TASK,
            DATE_TASK_TR,
            CLIENT_DEVAPP_NAME,
            DOC_REGISTRY_NAME,
            CURATOR_KI_FNM,
            DEV_KI_FNM,
            TST_KI_FNM,
            NAME_STATE_TASK,
            DOC_SENDER_COMMITTER,
            MEMO_TASK
        )
        val requestBody = GettingDataRequest(
            sessionId = functionSession,
            functionId = id,
            sboname = DEV_TASK,
            order = emptyList(),
            filter = filterList,
            reqlist = reqList
        )

        return api.getData(requestBody)
            .map { response ->
                response.values.map { mapper.fromSchemaToEntity(it) as PacketTask }
            }
            .flatMap { task ->
                dao.clearAllTask()
                    .andThen(dao.savePacketTask(task))
                    .andThen(dao.getTask())
            }
    }

    override fun loadPacketFunction(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String,
        idTask: Int
    ): Single<List<DisplayPacketFunction>> {
        val filterList = mutableListOf<Filter>().apply {
            add(Filter(PreferencesConstants.ID_TASK, EQUALS_PARAMS, idTask.toString()))
        }
        val requestBody = GettingDataRequest(
            sessionId = functionSession,
            functionId = id,
            sboname = DEV_TASK_FUNCTION_LIST,
            order = emptyList(),
            filter = filterList,
            reqlist = emptyList()
        )
        return api.getData(requestBody)
            .map { response ->
                response.values.map { packetFunctionMapper.fromSchemaToEntity(it) as DisplayPacketFunction }
            }
    }

    override fun getTaskById(id: Int): Single<PacketTask> {
        return dao.getTaskById(id)
    }
}