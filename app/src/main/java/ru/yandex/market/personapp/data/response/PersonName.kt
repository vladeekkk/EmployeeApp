package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * PersonName - дата класс для хранения имени человека на русском и английском языках
 */
data class PersonName(
    @SerializedName("ru")
    val personNameRu: String?,

    @SerializedName("en")
    val personNameEn: String?
)
