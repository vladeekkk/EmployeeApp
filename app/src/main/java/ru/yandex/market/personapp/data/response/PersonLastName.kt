package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * PersonLastName - дата класс для хранения фамилии человека на русском и английском языках
 */
data class PersonLastName(
    @SerializedName("ru")
    val lastNameRu: String?,

    @SerializedName("en")
    val lastNameEn: String?
)
