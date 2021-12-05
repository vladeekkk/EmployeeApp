package ru.yandex.market.personapp.di

import dagger.Module
import dagger.Provides
import ru.yandex.market.personapp.data.mappers.ResponseMapper
import ru.yandex.market.personapp.data.network.PersonAPI
import ru.yandex.market.personapp.data.repository.GetPersonsRxPagingSource

@Module
class SourceModule {

    @Provides
    fun provideSource(service: PersonAPI, mapper: ResponseMapper): GetPersonsRxPagingSource {
        return GetPersonsRxPagingSource(
            service = service,
            mapper = mapper
        )
    }
}