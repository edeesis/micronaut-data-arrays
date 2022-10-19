package com.edeesis.micronaut

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType

@MappedEntity
class Book(
    val title: String,
    val publisher: String,
    @field:TypeDef(type = DataType.STRING_ARRAY)
    val tags: Collection<String>,
    val genres: Array<String>,
    @field:Id @field:GeneratedValue val id: Long? = null
)