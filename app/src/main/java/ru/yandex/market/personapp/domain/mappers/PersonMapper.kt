package ru.yandex.market.personapp.domain.mappers

import ru.yandex.market.personapp.data.response.PersonInfo
import ru.yandex.market.personapp.domain.models.Person

/**
 * Класс `PersonMapper` - маппер.
 * Функцией [transform] преобразует [PersonInfo] в [Person]
 */
class PersonMapper {
    /**
     * Преобразует объект [personInfo] в объект типа Person
     * @param [personInfo] объект, хранящий в себе результат запроса
     * @return объект типа Person, хранящий только нужную информацию про всех людей
     */
    fun transform(personInfo: PersonInfo): Person {
        return Person(
            firstName = personInfo.personFullName?.firstNameRuEn?.personNameRu ?: "",
            secondName = personInfo.personFullName?.lastNameRuEn?.lastNameRu ?: "",
            login = personInfo.login ?: "no login data",
            cityName = personInfo.location?.office?.city?.cityName?.cityNameRu ?: "",
            birthday = personInfo.personal?.birthday ?: "",
            photoUrl = personInfo.images?.photo ?: ""
        )
    }
}
