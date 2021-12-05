package ru.yandex.market.personapp.presentation.formatters

import org.junit.Assert.*

import org.junit.Test
import ru.yandex.market.personapp.domain.models.Person

class PersonFormatterTest {

    @Test
    fun mapToPersonViewObject() {
        val formatter = PersonFormatter()
        val person1 = Person(
            "",
            "",
            "",
            "",
            "",
            ""
        )
        val person2 = Person(
            "name",
            "",
            "Login",
            "City",
            "1999-01-01",
            "someurl.jpg"
        )

        val person3 = Person(
            "name",
            "surname",
            "Login Login",
            "",
            "2009-01-01",
            "someurl/kek.jpg"
        )

        val personViewObject1 = formatter.mapToPersonViewObject(person1)
        val personViewObject2 = formatter.mapToPersonViewObject(person2)
        val personViewObject3 = formatter.mapToPersonViewObject(person3)
        assertEquals(personViewObject1.fullName, "")
        assertEquals(personViewObject2.fullName, "name")
        assertEquals(personViewObject3.fullName, "name surname")

        assertEquals(personViewObject1.login, "")
        assertEquals(personViewObject2.login, "Login")
        assertEquals(personViewObject3.login, "Login Login")

        assertEquals(personViewObject1.cityAgeString, "")
        assertEquals(personViewObject2.cityAgeString, "City, 22 года")
        assertEquals(personViewObject3.cityAgeString, "12 лет")

        assertEquals(personViewObject1.photoUrl, "")
        assertEquals(personViewObject2.photoUrl, "someurl/256.jpg")
        assertEquals(personViewObject3.photoUrl, "someurl/kek/256.jpg")
    }

}