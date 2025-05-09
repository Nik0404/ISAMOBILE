package com.example.isa.data.repositories

import com.example.isa.common.constants.PreferencesConstants.PACK_ID
import com.example.isa.common.constants.PreferencesConstants.EQUALS_PARAMS
import com.example.isa.common.constants.SboName.DEV_PACK_CONTENT
import com.example.isa.data.datasource.database.dao.PacketContentDao
import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.util.mappers.PacketContentMapper
import com.example.isa.domain.entity.local.database.PacketContent
import com.example.isa.domain.entity.local.display.DisplayPacketcontent
import com.example.isa.domain.entity.remote.request.GettingDataRequest
import com.example.isa.domain.entity.remote.request.other.Filter
import com.example.isa.domain.repositories.PacketContentRepository
import io.reactivex.Single

class PacketContentRepositoryImpl(
    private val api: FunctionsApi,
    private val dao: PacketContentDao,
    private val mapper: PacketContentMapper
) : PacketContentRepository {

    override fun loadPacketContent(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String,
        packId: Int
    ): Single<List<DisplayPacketcontent>> {
        val filterList = mutableListOf<Filter>().apply {
            add(Filter(PACK_ID, EQUALS_PARAMS, packId.toString()))
        }
        val requestBody = GettingDataRequest(
            sessionId = functionSession,
            functionId = id,
            sboname = DEV_PACK_CONTENT,
            order = emptyList(),
            filter = filterList,
            reqlist = emptyList()
        )

        return api.getData(requestBody)
            .map { response -> response.values.map { mapper.fromSchemaToEntity(it) as PacketContent } }
            .flatMap { packetContent ->
                dao.clearAllPack()
                    .andThen(dao.savePacketContent(packetContent))
                    .andThen(dao.getPacketsContent())
            }
    }
}