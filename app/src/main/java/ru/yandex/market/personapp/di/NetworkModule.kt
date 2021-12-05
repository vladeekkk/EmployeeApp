package ru.yandex.market.personapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.yandex.market.personapp.AUTH_HEADER
import ru.yandex.market.personapp.BASE_URL
import ru.yandex.market.personapp.OAUTH_TOKEN
import ru.yandex.market.personapp.data.network.NoConnectionInterceptor
import ru.yandex.market.personapp.data.network.PersonAPI

@Module
class NetworkModule {

    @Provides
    fun provideNoConnectionInterceptor(context: Context): NoConnectionInterceptor {
        return NoConnectionInterceptor(context)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        noConnectionInterceptor: NoConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(noConnectionInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(AUTH_HEADER, "OAuth $OAUTH_TOKEN")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): PersonAPI {
        return retrofit.create(PersonAPI::class.java)
    }

}