package com.example.isa.domain.entity.remote.response

import com.example.isa.domain.entity.remote.response.other.ReqsInfo
import com.google.gson.annotations.SerializedName

class SelectResponse(
    @SerializedName("values") val values: List<List<String>>,
    @SerializedName("reqsInfo") val reqsInfo: List<ReqsInfo>
)