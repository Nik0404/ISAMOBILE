package com.example.isa.domain.entity.remote.request

import com.example.isa.domain.entity.remote.request.values.AcronValue
import com.google.gson.annotations.SerializedName

class ModifyingDataRequest(
    @SerializedName("sessionid") val sessionId: String,
    @SerializedName("functionid") val functionId: Int,
    @SerializedName("sboname") val sboName: String,
    @SerializedName("values") val values: AcronValue

)