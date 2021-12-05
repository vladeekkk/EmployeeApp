package ru.yandex.market.personapp.di

import dagger.Module
import dagger.Provides
import ru.yandex.market.personapp.data.repository.GetPersonsRxPagingSource
import ru.yandex.market.personapp.data.repository.GetPersonsRxRepository
import ru.yandex.market.personapp.data.repository.GetPersonsRxRepositoryImpl

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(source: GetPersonsRxPagingSource): GetPersonsRxRepository {
        return GetPersonsRxRepositoryImpl(source)
    }
}