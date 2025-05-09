package com.example.isa.data.repositories

import com.example.isa.data.datasource.network.FunctionsApi
import com.example.isa.domain.entity.remote.request.GettingDataRequest
import com.example.isa.domain.entity.remote.request.other.Filter
import com.example.isa.domain.entity.remote.response.SelectResponse
import com.example.isa.domain.repositories.FunctionRepository
import com.example.isa.domain.repositories.PreferencesRepository
import io.reactivex.Single

class FunctionRepositoryImpl(
    private val functionApi: FunctionsApi,
    private val preferencesRepository: PreferencesRepository
) : FunctionRepository {

    override fun getData(
        sessionIdF: String,
        functionId: Int,
        sboname: String,
        filter: List<Filter>,
        reqList: List<String>
    ): Single<SelectResponse> {
        val dataRequest =
            GettingDataRequest(sessionIdF, functionId, sboname, emptyList(), filter, reqList)

        return functionApi.getData(dataRequest)
    }
}
