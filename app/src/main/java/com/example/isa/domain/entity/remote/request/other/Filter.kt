package com.example.isa.domain.entity.remote.request.other

import com.google.gson.annotations.SerializedName

class Filter(
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("value") val value: String
)
