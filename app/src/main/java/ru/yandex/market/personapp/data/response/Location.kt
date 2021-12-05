package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("office")
    val office: Office?
)
