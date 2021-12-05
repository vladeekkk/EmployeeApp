package ru.yandex.market.personapp.data.response

import com.google.gson.annotations.SerializedName

/**
 * Personal - дата класс для хранения дня рождения пользователя
 * @property birthday дата в формате yyyy-mm-dd
 */

data class Personal(
    @SerializedName("birthday")
    val birthday: String?
)
