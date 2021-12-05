package ru.yandex.market.personapp.di

import dagger.Module
import dagger.Provides
import ru.yandex.market.personapp.data.repository.GetPersonsRxRepository
import ru.yandex.market.personapp.domain.mappers.PersonMapper
import ru.yandex.market.personapp.domain.usecases.GetPersonsUsecase

@Module
class UsecaseModule {

    @Provides
    fun provideGetPersonsUsecase(
        repository: GetPersonsRxRepository,
        mapper: PersonMapper
    ): GetPersonsUsecase {
        return GetPersonsUsecase(repository, mapper)
    }
}