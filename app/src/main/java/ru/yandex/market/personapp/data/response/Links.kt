package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * Links - дата класс для хранения ссылок на предыдущую и следующую страницу запроса
 */
data class Links(
    @SerializedName("last")
    val last: String?,

    @SerializedName("next")
    val next: String?
)

