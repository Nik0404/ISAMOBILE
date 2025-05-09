package com.example.isa.domain.entity.local.display

import com.example.isa.domain.entity.local.database.AcronEntity

class DisplayPacketFunction(
    val idTask: Int,
    val functionId: Int,
    val functionIdFnm: String,
    val writeAccess: Int,
    val ready: Int,
    val scope: Int,
    val scopeSnm: String,
    val subsystem: Int,
    val subsystemFnm: String,
    val subsystemSnm: String,
    val dbObjPrefix: String,
    val isIsa2005: String,
    val isIsa2010: String,
    val isIsaWeb: String,
    val isStartIsaWeb: String
) : AcronEntity