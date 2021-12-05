package ru.yandex.market.personapp.di

import dagger.Module
import dagger.Provides
import ru.yandex.market.personapp.presentation.formatters.PersonFormatter

@Module
class FormattersModule {

    @Provides
    fun providePersonFormatter(): PersonFormatter {
        return PersonFormatter()
    }
}