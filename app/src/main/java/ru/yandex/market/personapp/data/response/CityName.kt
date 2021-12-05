package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * CityName - дата класс для хранения названия города на русском и английском языках
 */
data class CityName(
    @SerializedName("ru")
    val cityNameRu: String?,
    @SerializedName("en")
    val cityNameEn: String?
)
