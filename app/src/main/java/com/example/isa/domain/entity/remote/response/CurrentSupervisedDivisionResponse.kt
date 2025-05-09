package com.example.isa.domain.entity.remote.response

import com.google.gson.annotations.SerializedName

class CurrentSupervisedDivisionResponse(
    @SerializedName("divisionName") val divisionName: String
)