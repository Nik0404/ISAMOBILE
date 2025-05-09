package com.example.isa.data.repositories

import com.example.isa.data.datasource.database.dao.PacketNegotiationDao
import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.data.util.mappers.PacketNegotiationMapper
import com.example.isa.domain.entity.local.database.PacketNegotiation
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation
import com.example.isa.domain.entity.remote.request.GettingDataRequest
import com.example.isa.domain.entity.remote.request.ModifyingDataRequest
import com.example.isa.domain.entity.remote.request.other.Filter
import com.example.isa.domain.entity.remote.request.values.PacketNegotiationSignedValue
import com.example.isa.domain.repositories.PacketNegotiationRepository
import io.reactivex.Completable
import io.reactivex.Single

class PacketNegotiationRepositoryImpl(
    private val api: FunctionsApi,
    private val dao: PacketNegotiationDao,
    private val mapper: PacketNegotiationMapper
) : PacketNegotiationRepository {

    override fun loadPacketNegotiation(
        functionSession: String,
        id: Int,
        applicationCode: String,
        applicationVersion: String
    ): Completable {
        val filterList = mutableListOf<Filter>()
        filterList.add(Filter("379390", "in", "MAIN_URC_CURATOR, URC_CURATOR"))
        val requestBody =
            GettingDataRequest(
                sessionId = functionSession,
                functionId = id,
                sboname = "DEV_PACK_AGREEMENT",
                order = emptyList(),
                filter = emptyList(),
                reqlist = emptyList()
            )

        return api.getData(requestBody)
            .map { response ->
                response.values.map {
                    mapper.fromSchemaToEntity(it) as PacketNegotiation
                }
            }
            .flatMapCompletable { packet ->
                dao.savePacketNegotiation(packet)
            }
    }

    override fun getPacketNegotiation(): Single<List<DisplayPacketNegotiation>> {
        return dao.getPacketNegotiationAll()
    }

    override fun changeSigned(
        sessionId: String,
        id: Int,
        sboName: String,
        packId: String,
        urcCurator: String,
        urcSignTime: String
    ): Completable {
        val packetValue = PacketNegotiationSignedValue(packId, urcCurator, urcSignTime)
        val requestBody = ModifyingDataRequest(sessionId, id, sboName, packetValue)

        return api.changeIsSigned(requestBody)
            .andThen(loadPacketNegotiation(sessionId, id, sboName, packId))
    }

    override fun getPacketNegotiationId(packetId: Int): Single<PacketNegotiation> {
        return dao.getPacketById(packetId)
    }


}