package ru.yandex.market.personapp.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import io.reactivex.Observable
import ru.yandex.market.personapp.data.repository.GetPersonsRxRepository
import ru.yandex.market.personapp.domain.mappers.PersonMapper
import ru.yandex.market.personapp.domain.models.Person
import javax.inject.Inject

class GetPersonsUsecase @Inject constructor(
    private val repository: GetPersonsRxRepository,
    private val mapper: PersonMapper
) {
    fun getPersons(): Observable<PagingData<Person>> {
        return repository.getPersons().map {
            it.map { personInfo ->
                mapper.transform(personInfo)
            }
        }
    }
}