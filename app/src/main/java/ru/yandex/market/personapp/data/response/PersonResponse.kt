package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * PersonResponse - дата класс для хранения результата запроса
 * @property links ссылки на предыдущую и следующую страницу запроса
 * @property page текущая страница
 * @property limit лимит на количество пользователей в одном запросе
 * @property result список объектов [PersonInfo] информации про каждого человека
 */
data class PersonResponse(
    @SerializedName("links")
    val links: Links?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("limit")
    val limit: Int?,

    @SerializedName("result")
    val result: List<PersonInfo>?
)
