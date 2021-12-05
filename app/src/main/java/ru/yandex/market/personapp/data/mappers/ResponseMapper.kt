package ru.yandex.market.personapp.data.mappers

import androidx.paging.PagingSource
import ru.yandex.market.personapp.data.response.PersonInfo
import ru.yandex.market.personapp.data.response.PersonResponse

class ResponseMapper {
    fun toLoadResult(data: PersonResponse, position: Int): PagingSource.LoadResult<Int, PersonInfo> {
        return PagingSource.LoadResult.Page(
            data = data.result ?: emptyList(),
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.limit) null else position + 1
        )
    }
}