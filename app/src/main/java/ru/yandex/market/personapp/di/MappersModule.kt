package ru.yandex.market.personapp.di

import dagger.Module
import dagger.Provides
import ru.yandex.market.personapp.data.mappers.ResponseMapper
import ru.yandex.market.personapp.domain.mappers.PersonMapper

@Module
class MappersModule {

    @Provides
    fun provideResponseMapper(): ResponseMapper {
        return ResponseMapper()
    }

    @Provides
    fun providePersonMapper(): PersonMapper {
        return PersonMapper()
    }
}