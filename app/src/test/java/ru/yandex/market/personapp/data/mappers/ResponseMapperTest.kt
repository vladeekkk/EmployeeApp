package ru.yandex.market.personapp.data.mappers

import androidx.paging.PagingSource
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import ru.yandex.market.personapp.data.response.PersonInfo
import ru.yandex.market.personapp.data.response.PersonResponse

class ResponseMapperTest {

    lateinit var mapper: ResponseMapper
    @Before
    fun setUp() {
        mapper = ResponseMapper()
    }

    @Test
    fun toLoadResult() {
        val personResponse = PersonResponse(null, null, null, null)

        val expectedResult = PagingSource.LoadResult.Page(
            data = emptyList<PersonInfo>(),
            prevKey = null,
            nextKey = 2
        )
        val actualResult = mapper.toLoadResult(personResponse, 1)
        assertEquals(expectedResult, actualResult)
    }
}