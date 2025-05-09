package com.example.isa.domain.entity.remote.response

import com.google.gson.annotations.SerializedName

class UserInfoResponse(
    @SerializedName("FIO") val userFullNameResponse: UserFullNameResponse,
    @SerializedName("currentSupervisedDivision") val currentSupervisedDivision: CurrentSupervisedDivisionResponse,
    @SerializedName("email") val email: String?,
    @SerializedName("post") val post: String?,
    @SerializedName("phone") val phone: String?
//    @SerializedName("defaultSupervisedDivision") val defaultSupervisedDivision: DivisionSchema
)
