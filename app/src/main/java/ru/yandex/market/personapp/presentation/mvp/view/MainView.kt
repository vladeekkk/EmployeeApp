package ru.yandex.market.personapp.presentation.mvp.view

import androidx.paging.PagingData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.yandex.market.personapp.presentation.viewobjects.PersonViewObject

/**
 * Интерфейс view для [MainActivity]
 */
interface MainView: MvpView {

    @AddToEndSingle
    fun initViews()

    @AddToEndSingle
    fun showToastMessage(message: String)

    @AddToEndSingle
    fun submitDataToAdapter(data: PagingData<PersonViewObject>)

    @AddToEndSingle
    fun updateRefreshing(state: Boolean)

    @AddToEndSingle
    fun showAlertDialogAndRetry(titleRes: Int, messageRes: Int)
}