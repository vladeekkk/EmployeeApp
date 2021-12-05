package ru.yandex.market.personapp.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.yandex.market.personapp.FIELDS
import ru.yandex.market.personapp.data.mappers.ResponseMapper
import ru.yandex.market.personapp.data.network.PersonAPI
import ru.yandex.market.personapp.data.response.PersonInfo
import javax.inject.Inject

/**
 * Класс `GetPersonsRxPagingSource` отвечает за запросы данных, маппинг, пагинацию
 * @param service для запросов через Retrofit
 * @param mapper маппер из ResponsePerson в модель PersonInfo
 */
class GetPersonsRxPagingSource @Inject constructor(
    private val service: PersonAPI,
    private val mapper: ResponseMapper
) : RxPagingSource<Int, PersonInfo>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PersonInfo>> {
        val position: Int = params.key ?: 1
        return service.getData(FIELDS, position)
            .subscribeOn(Schedulers.io())
            .map { mapper.toLoadResult(it, position) }
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { LoadResult.Error(it) }
    }

    override fun getRefreshKey(state: PagingState<Int, PersonInfo>): Int? {
        return state.anchorPosition
    }
}
