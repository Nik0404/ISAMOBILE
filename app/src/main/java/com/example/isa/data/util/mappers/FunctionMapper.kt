package com.example.isa.data.util.mappers

import com.example.isa.data.util.mappers.base.EntityMapper
import com.example.isa.domain.entity.local.database.UserFunction
import com.example.isa.domain.entity.remote.schema.FunctionSchema

class FunctionMapper : EntityMapper<FunctionSchema, UserFunction> {
    override fun fromSchemaToEntity(scheme: FunctionSchema): UserFunction {
        return UserFunction(scheme.functionCode, scheme.functionName, scheme.functionTitle)
    }
}
