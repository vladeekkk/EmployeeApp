package ru.yandex.market.personapp.domain.models

/**
 * Person - модель. Дата класс для хранения информации об одном человеке
 * @property firstName имя человека на русском языке
 * @property secondName фамилия человека на русском языке
 * @property login логин человека
 * @property cityName город человека
 * @property birthday день рождения человека
 * @property photoUrl url фотографии человека
 */
data class Person(
    val firstName: String,
    val secondName: String,
    val login: String,
    val cityName: String,
    val birthday: String,
    val photoUrl: String
)
