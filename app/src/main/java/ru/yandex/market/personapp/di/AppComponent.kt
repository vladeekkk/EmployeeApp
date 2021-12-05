package ru.yandex.market.personapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.disposables.CompositeDisposable
import ru.yandex.market.personapp.PersonApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, AndroidInjectionModule::class,
        ActivityModule::class, NetworkModule::class,
        FormattersModule::class, MappersModule::class,
        UsecaseModule::class, RepositoryModule::class,
        SourceModule::class
    ]
)
interface AppComponent : AndroidInjector<PersonApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

}

@Module
object AppModule {

    @Provides
    fun providesDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}