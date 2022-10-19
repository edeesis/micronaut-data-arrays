package com.edeesis.micronaut

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import kotlinx.coroutines.flow.toList
import java.util.*

@MicronautTest(transactional = false)
class BookRepositoryTest(
    private val repository: BookRepository
): StringSpec({
    beforeAny {
        if (repository.count() != 2L) {
            val book = Book("title ${UUID.randomUUID()}", "publisher", listOf("one", "two"), arrayOf("three", "four"))
            val book2 = Book("title ${UUID.randomUUID()}", "publisher", listOf("one", "two"), arrayOf("three", "four"))
            repository.saveAll(listOf(book, book2)).toList()
        }
    }

    repository::findTitlesGroupedByPublisherList.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 1
            ret.first().size shouldBe 2
        }
    }

    repository::findTitlesGroupedByPublisherListDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 1
            ret.first().list.size shouldBe 2
        }
    }

    repository::findTitlesGroupedByPublisherArrayDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 1
            ret.first().list.size shouldBe 2
        }
    }

    repository::findTitlesGroupedByPublisherListTypeDefDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 1
            ret.first().list.size shouldBe 2
        }
    }

    repository::findTitlesGroupedByPublisherArrayTypeDefDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 1
            ret.first().list.size shouldBe 2
        }
    }


    repository::findTagsList.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 2
            ret.first().size shouldBe 2
        }
    }

    repository::findTagsListDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 2
            ret.first().list.size shouldBe 2
        }
    }

    repository::findTagsArrayDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 2
            ret.first().list.size shouldBe 2
        }
    }

    repository::findTagsListTypeDefDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 2
            ret.first().list.size shouldBe 2
        }
    }

    repository::findTagsArrayTypeDefDTO.let {
        it.name {
            val ret = it.call()
            ret.size shouldBe 2
            ret.first().list.size shouldBe 2
        }
    }
})