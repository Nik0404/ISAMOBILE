package com.example.isa.domain.entity.remote.response

import com.example.isa.domain.entity.remote.schema.FunctionSchema
import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("sessionid") val sessionId: String,
    @SerializedName("Functions") val functions: List<FunctionSchema>
)