package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * CityInfo - дата класс для хранения информации о стране и городе человека
 */
data class CityInfo(
    @SerializedName("name")
    val cityName: CityName?
)
