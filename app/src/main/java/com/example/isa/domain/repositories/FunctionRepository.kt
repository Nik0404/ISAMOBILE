package com.example.isa.domain.repositories

import com.example.isa.domain.entity.remote.request.other.Filter
import com.example.isa.domain.entity.remote.response.SelectResponse
import io.reactivex.Single

interface FunctionRepository {

    fun getData(
        sessionIdF: String,
        functionId: Int,
        sboname: String,
        filter: List<Filter>,
        reqList: List<String>
    ): Single<SelectResponse>

}
