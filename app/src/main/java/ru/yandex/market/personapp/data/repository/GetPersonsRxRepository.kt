package ru.yandex.market.personapp.data.repository

import androidx.paging.PagingData
import io.reactivex.Observable
import ru.yandex.market.personapp.data.response.PersonInfo

interface GetPersonsRxRepository {
    fun getPersons(): Observable<PagingData<PersonInfo>>
}