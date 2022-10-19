package com.edeesis.micronaut

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface BookRepository : CoroutineCrudRepository<Book, Long> {

    companion object {
        const val GROUP_QUERY = "SELECT ARRAY_AGG(title) as list FROM Book GROUP BY publisher"
        const val TAGS_QUERY = "SELECT tags as list FROM Book"
    }

    @Query(GROUP_QUERY)
    fun findTitlesGroupedByPublisherList(): List<List<String>>

    @Query(GROUP_QUERY)
    fun findTitlesGroupedByPublisherListDTO(): List<ListDTO>

    @Query(GROUP_QUERY)
    fun findTitlesGroupedByPublisherArrayDTO(): List<ArrayDTO>

    @Query(GROUP_QUERY)
    fun findTitlesGroupedByPublisherListTypeDefDTO(): List<ListTypeDefDTO>

    @Query(GROUP_QUERY)
    fun findTitlesGroupedByPublisherArrayTypeDefDTO(): List<ArrayTypeDefDTO>

    @Query(TAGS_QUERY)
    fun findTagsList(): List<List<String>>

    @Query(TAGS_QUERY)
    fun findTagsListDTO(): List<ListDTO>

    @Query(TAGS_QUERY)
    fun findTagsArrayDTO(): List<ArrayDTO>

    @Query(TAGS_QUERY)
    fun findTagsListTypeDefDTO(): List<ListTypeDefDTO>

    @Query(TAGS_QUERY)
    fun findTagsArrayTypeDefDTO(): List<ArrayTypeDefDTO>
}

@Introspected
class ListDTO(val list: List<String>)

@Introspected
class ArrayDTO(val list: Array<String>)

@Introspected
class ListTypeDefDTO(@TypeDef(type = DataType.STRING_ARRAY) val list: List<String>)

@Introspected
class ArrayTypeDefDTO(@TypeDef(type = DataType.STRING_ARRAY) val list: Array<String>)