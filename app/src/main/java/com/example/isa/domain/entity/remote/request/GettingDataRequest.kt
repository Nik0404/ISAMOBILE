package com.example.isa.domain.entity.remote.request

import com.example.isa.domain.entity.remote.request.other.Filter
import com.example.isa.domain.entity.remote.request.other.Order
import com.google.gson.annotations.SerializedName

class GettingDataRequest(
    @SerializedName("sessionid") val sessionId: String,
    @SerializedName("functionid") val functionId: Int,
    @SerializedName("sboname") val sboname: String,
    @SerializedName("order") val order: List<Order>,
    @SerializedName("filter") val filter: List<Filter>,
    @SerializedName("reqlist") val reqlist: List<String> = listOf()
)
