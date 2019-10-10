package ru.nickb.cryptotop.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nickb.cryptotop.di.App
import ru.nickb.cryptotop.mvp.view.LatestChartView
import ru.nickb.cryptotop.rest.CoinGeckoApi
import javax.inject.Inject

@InjectViewState
class LatestChartPresenter : BasePresenter<LatestChartView>() {

    @Inject
    lateinit var geckoApi: CoinGeckoApi


    init {
        App.appComponent.inject(this)
    }

     fun makeChart(id: String) {
        subscribe(geckoApi.getCoinMarketChart(id)


            .map { it.prices }

            .flatMap { Observable.fromIterable(it) }

            .doOnComplete {

                viewState.hideProgress()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.hideProgress()
                viewState.addEntryToChart(it[0], it[1])

            }, {
                viewState.hideProgress()
                viewState.showErrorMessage(it.message)
                it.printStackTrace()
            }))
         /*viewState.subscribe(geckoApi.getCoinMarketChart(id)


            .map { it.prices }

            .flatMap { Observable.fromIterable(it) }

            .doOnComplete {

                viewState.hideProgress()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.hideProgress()
                viewState.addEntryToChart(it[0], it[1])

            }, {
                viewState.hideProgress()
                viewState.showErrorMessage(it.message)
                it.printStackTrace()
            })
        )*/

    }

     fun refreshChart() {
        viewState.refresh()

    }

}