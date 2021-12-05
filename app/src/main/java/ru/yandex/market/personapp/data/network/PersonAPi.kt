package ru.yandex.market.personapp.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yandex.market.personapp.FIELDS
import ru.yandex.market.personapp.data.response.PersonResponse

/**
 * PersonAPI - интерфейс для Retrofit для отправки запроса и получения данных
 */
interface PersonAPI {
    /**
     * Метод для Retrofit для отправки запроса и получения данных
     * @param fields поля, какие данные из запроса нужно получить
     * @param page страница запроса
     * @return [PersonResponse], обёрнутый в Single для работы с rxjava
     */
    @GET("v3/persons")
    fun getData(@Query("_fields")
                fields: String = FIELDS,
                @Query("_page")
                page: Int) : Single<PersonResponse>
}
