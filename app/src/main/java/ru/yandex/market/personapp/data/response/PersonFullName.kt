package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * PersonFullName - дата класс для хранения имени [PersonName] и фамилии [PersonLastName] человека на русском и английском языках
 * @property firstNameRuEn имя на русском и английском языке
 * @property lastNameRuEn фамилия на русском и английском языке,  Nullable - фамилии может не быть
 */
data class PersonFullName(
    @SerializedName("first")
    val firstNameRuEn: PersonName?,
    @SerializedName("last")
    val lastNameRuEn: PersonLastName?
)
