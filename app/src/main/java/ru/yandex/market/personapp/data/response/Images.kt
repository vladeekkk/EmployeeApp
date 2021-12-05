package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * Images - дата класс для хранения url фото человека
 */
data class Images(
    @SerializedName("photo")
    val photo: String?
)
