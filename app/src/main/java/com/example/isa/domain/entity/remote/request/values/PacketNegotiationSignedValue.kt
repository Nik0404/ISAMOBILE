package com.example.isa.domain.entity.remote.request.values

import com.example.isa.data.util.constants.PacketNegotiationColumns
import com.google.gson.annotations.SerializedName

class PacketNegotiationSignedValue(
    @SerializedName(PacketNegotiationColumns.PACK_ID_COLUMN_NAME) val packId: String,
    @SerializedName(PacketNegotiationColumns.URC_CURATOR_COLUMN_NAME) val urcCurator: String,
    @SerializedName("SIGN_TIME") val signTime: String
) : AcronValue