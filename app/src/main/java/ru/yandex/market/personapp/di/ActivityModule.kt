package ru.yandex.market.personapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.yandex.market.personapp.presentation.mvp.view.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            AppModule::class
        ]
    )
    abstract fun bindsMainActivity(): MainActivity

}