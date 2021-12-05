package ru.yandex.market.personapp.presentation.mvp.presenter

import androidx.paging.map
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.yandex.market.personapp.domain.usecases.GetPersonsUsecase
import ru.yandex.market.personapp.presentation.formatters.PersonFormatter
import ru.yandex.market.personapp.presentation.mvp.view.MainView
import javax.inject.Inject

import androidx.paging.LoadState
import androidx.paging.rxjava2.cachedIn
import moxy.presenterScope
import ru.yandex.market.personapp.R
import ru.yandex.market.personapp.data.network.NoConnectivityException


/**
 * Класс `MainPresenterImpl` - имплементация [MainPresenter]. Выполняет роль презентера в mvp.
 */
@InjectViewState
class MainPresenterImpl @Inject constructor(
    private val usecase: GetPersonsUsecase,
    private val formatter: PersonFormatter,
    private val mDisposable: CompositeDisposable
) : MvpPresenter<MainView>(), MainPresenter {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initViews()
        onReadyToGetPersons()
    }

    /**
     * Получение данных с сервера, маппинг, отправка их в адаптер RecyclerView
     */
    override fun onReadyToGetPersons() {
        mDisposable.add(
            usecase.getPersons()
                .cachedIn(presenterScope)
                .subscribe {
                    viewState.submitDataToAdapter(it.map { person ->
                        formatter.mapToPersonViewObject(person)
                    })
                }
        )
        viewState.updateRefreshing(false)
    }

    override fun onGetError(loadError: LoadState.Error?) {
        if (loadError?.error is NoConnectivityException || loadError?.error != null) {
            viewState.showAlertDialogAndRetry(
                R.string.noInternet, R.string.toReload)
        }
    }

    override fun cleanPresenter() {
        mDisposable.dispose()
    }
}