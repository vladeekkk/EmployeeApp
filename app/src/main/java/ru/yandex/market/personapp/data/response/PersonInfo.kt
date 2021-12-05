package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * PersonInfo - дата класс для хранения данных об одном человеке
 * @property images ссылки на аватарку, фото пользователя
 * @property personal данные про день рождения (нужно для отображения возраста)
 * @property login логин человека
 * @property location данные про страну, город пользователя (нужно для отображения города)
 * @property personFullName имя и фамлия человека на русском, английском языках
 */
data class PersonInfo(

    @SerializedName("images")
    val images: Images?,

    @SerializedName("personal")
    val personal: Personal?,

    @SerializedName("login")
    val login: String?,

    @SerializedName("location")
    val location: Location?,

    @SerializedName("name")
    val personFullName: PersonFullName?
)
