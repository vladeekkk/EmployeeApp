package ru.yandex.market.personapp.presentation.formatters

import ru.yandex.market.personapp.domain.models.Person
import ru.yandex.market.personapp.presentation.viewobjects.PersonViewObject
import java.util.*

class PersonFormatter {
    fun mapToPersonViewObject(person: Person): PersonViewObject {
        return PersonViewObject(
            fullName = getPersonFullName(person),
            login = person.login,
            cityAgeString = getCityAgeString(person),
            photoUrl = getCorrectPhotoUrl(person.photoUrl)
        )
    }

    /**
     * Возращает строку с городом и возрастом человека [person] через пробел на русском языке.
     * Если информации про возраст нет, возвращает город. Если информации про город нет, возвращает возраст
     * Возвращает пустую строку в случае отсутствия и возраста, и города
     */
    private fun getCityAgeString(person: Person): String {
        val ageString = getAgeString(person.birthday)
        val cityString = person.cityName
        if (cityString.isEmpty() && ageString.isEmpty()) {
            return ""
        }

        if (cityString.isEmpty()) {
            return ageString
        }

        if (ageString.isEmpty()) {
            return cityString
        }
        return "$cityString, $ageString"
    }
    /**
     *  Возвращает строку с именем и фамилией человека [person] через пробел на русском языке.
     *  Если информации про фамилию нет, возвращает имя. Если информации про имя нет - пустую строку
     */
    private fun getPersonFullName(person: Person): String {
        if (person.firstName.isEmpty()) {
            return ""
        }
        if (person.secondName.isEmpty()) {
            return person.firstName
        }
        return "${person.firstName} ${person.secondName}"
    }

    /**
     * Считает по дате рождения текущий возраст
     * @param birthday - день рождения.
     * Информации про возраст может не быть - в таком случае возвращается пустая строка
     */
    private fun getAgeString(birthday: String?): String {
        if (birthday.isNullOrEmpty()) {
            return ""
        }

        val age: Int = getAgeByBirthday(birthday)
        val lastDigit = age % 10

        if (age in 10..20 || lastDigit >= 5 || lastDigit == 0) {
            return "$age лет"
        }

        if (lastDigit == 1) {
            return "$age год"
        }

        return "$age года"
    }

    private fun getAgeByBirthday(birthday: String): Int {
        val yearBirth = (birthday.subSequence(0, 4)).toString().toInt()
        val monthBirth = birthday.subSequence(5, 7).toString().toInt()
        val dayBirth = birthday.subSequence(8, 10).toString().toInt()

        val calendar = Calendar.getInstance()
        val yearToday: Int = calendar.get(Calendar.YEAR)
        val monthToday: Int = calendar.get(Calendar.MONTH)
        val dayToday: Int = calendar.get(Calendar.DAY_OF_MONTH)

        var age: Int = yearToday - yearBirth
        if (monthToday < monthBirth || monthToday == monthBirth && dayToday < dayBirth)
            age--

        return age
    }

    /**
     * Преобразует url в приемлемый формат для отображения
     * @param url ссылка на фото.
     */
    private fun getCorrectPhotoUrl(url: String): String {
        if (url.isEmpty()) {
            return ""
        }
        val format = url.substring(url.length - 4, url.length)
        val pictureShowParameter = "/256"
        return url.substring(0, url.length - 4) + pictureShowParameter + format
    }
}