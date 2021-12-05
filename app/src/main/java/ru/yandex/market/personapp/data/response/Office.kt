package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

data class Office(
    @SerializedName("city")
    val city: CityInfo?
)
