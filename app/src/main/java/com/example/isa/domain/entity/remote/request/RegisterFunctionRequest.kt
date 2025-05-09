package com.example.isa.domain.entity.remote.request

import com.google.gson.annotations.SerializedName

class RegisterFunctionRequest(
    @SerializedName("sessionid") val sessionId: String,
    @SerializedName("functionid") val functionId: Int,
    @SerializedName("applicationcode") val applicationCode: String,
    @SerializedName("applicationversion") val applicationVersion: String
)

