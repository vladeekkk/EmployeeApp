package ru.yandex.market.personapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import io.reactivex.Observable
import ru.yandex.market.personapp.data.response.PersonInfo
import javax.inject.Inject

class GetPersonsRxRepositoryImpl
@Inject constructor(private val pagingSource: GetPersonsRxPagingSource) :
    GetPersonsRxRepository {

    /**
     * Установка параметров пагинации, упаковка в Observable
     */
    override fun getPersons(): Observable<PagingData<PersonInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                maxSize = MAX_SIZE,
                prefetchDistance = PREFETCH_DIST,
                initialLoadSize = INIT_LOAD_SIZE
            ),
            pagingSourceFactory = { pagingSource }
        ).observable
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
        private const val MAX_SIZE: Int = 30
        private const val PREFETCH_DIST: Int = 5
        private const val INIT_LOAD_SIZE: Int = 30
    }
}
