package ru.yandex.market.personapp.presentation.mvp.view

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.yandex.market.personapp.R
import ru.yandex.market.personapp.databinding.ActivityMainBinding
import ru.yandex.market.personapp.presentation.list.PersonsRxAdapter
import ru.yandex.market.personapp.presentation.mvp.presenter.MainPresenterImpl
import ru.yandex.market.personapp.presentation.viewobjects.PersonViewObject
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : MvpAppCompatActivity(), MainView, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var adapter: PersonsRxAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenterProvider: Provider<MainPresenterImpl>

    @InjectPresenter
    lateinit var presenter: MainPresenterImpl

    @ProvidePresenter
    fun providePresenter(): MainPresenterImpl {
        return presenterProvider.get()
    }

    /**
     * Создание биндинга
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initViews() {
        adapter = PersonsRxAdapter(this)
        addErrorListenerToAdapter()

        initSwipeRefreshAndAddListener()

        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun submitDataToAdapter(data: PagingData<PersonViewObject>) {
        adapter.submitData(lifecycle, data)
    }

    override fun updateRefreshing(state: Boolean) {
        swipeRefreshLayout.isRefreshing = state
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanPresenter()
    }

    private fun initSwipeRefreshAndAddListener() {
        swipeRefreshLayout = binding.swiperefresh
        swipeRefreshLayout.setOnRefreshListener {
            presenter.onReadyToGetPersons()
        }
    }

    private fun addErrorListenerToAdapter() {
        adapter.addLoadStateListener { loadState ->
            val loadStateError = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.append as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error

            presenter.onGetError(loadStateError)
        }
    }

    override fun showAlertDialogAndRetry(titleRes: Int, messageRes: Int) {
        AlertDialog.Builder(this)
            .setTitle(getString(titleRes))
            .setPositiveButton(
                getString(messageRes)
            ) { dialog, _ ->
                adapter.retry()
                dialog.cancel()
            }
            .setNegativeButton(getString(R.string.closeDialog)) { dialog, _ -> dialog.cancel() }
            .show()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}