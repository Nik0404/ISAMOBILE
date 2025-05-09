package com.example.isa.data.util.mappers.base

interface EntityMapper<S, E> {
    fun fromSchemaToEntity(schema: S): E
}
