package ru.yandex.market.personapp.presentation.mvp.presenter

import androidx.paging.LoadState
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainPresenter {

    @AddToEndSingle
    fun onReadyToGetPersons()

    @AddToEndSingle
    fun cleanPresenter()

    @AddToEndSingle
    fun onGetError(error: LoadState.Error?)

}