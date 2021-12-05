package ru.yandex.market.personapp.domain.mappers

import org.junit.Assert.*

import org.junit.Test
import ru.yandex.market.personapp.data.response.*

class PersonMapperTest {

    @Test
    fun transform() {
        val mapper = PersonMapper()
        val personInfo1 = PersonInfo(
            Images(""),
            Personal("1999-01-01"),
        "Login",
            Location(Office(CityInfo(CityName("ruName", "enName")))),
            PersonFullName(
                PersonName("ruNamePerson", "enNamePerson"),
            PersonLastName("ruLastName", "enLastName"))
            )
        val personInfo2 = PersonInfo(
            Images(null),
            Personal("1999-01-01"),
            null,
            Location(Office(CityInfo(CityName("ruName", null)))),
            PersonFullName(
                PersonName(null, null),
                PersonLastName(null, null))
        )

        val person1 = mapper.transform(personInfo1)
        assertEquals(person1.birthday, "1999-01-01")
        assertEquals(person1.cityName, "ruName")
        assertEquals(person1.firstName, "ruNamePerson")
        assertEquals(person1.secondName, "ruLastName")
        assertEquals(person1.photoUrl, "")

        val person2 = mapper.transform(personInfo2)
        assertEquals(person2.birthday, "1999-01-01")
        assertEquals(person2.login, "no login data")
        assertEquals(person2.cityName, "ruName")
        assertEquals(person2.firstName, "")
        assertEquals(person2.secondName, "")
        assertEquals(person2.photoUrl, "")
    }
}