package ru.nickb.cryptotop.mvp.presenter


import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nickb.cryptotop.adapter.CurrenciesAdapter
import ru.nickb.cryptotop.di.App
import ru.nickb.cryptotop.formatThousands
import ru.nickb.cryptotop.mvp.view.CurrenciesView
import ru.nickb.cryptotop.rest.CoinGeckoApi
import javax.inject.Inject

@InjectViewState
class CurrenciesPresenter : BasePresenter<CurrenciesView>() {

    //внедряем источник данных
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    //инициализируем компоненты Даггера
    init {
        App.appComponent.inject(this)

    }

    //создаем список, загружая данные с помощью RxJava
  fun makeList() {
        viewState.showProgress()

        //подписываемся на поток данных
        subscribe(geckoApi.getCoinMarket()

            //определяем отдельный поток для отправки данных
            .subscribeOn(Schedulers.io())

            //получаем данные в основном потоке
            .observeOn(AndroidSchedulers.mainThread())

            //преобразуем List<GeckoCoin> в Observable<GeckoCoin>
            .flatMap { Observable.fromIterable(it) }

            //наполняем поля элемента списка для адаптера
            .doOnNext {
                viewState.addCurrency(
                CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }

            //вызывается при вызове onComplete
            .doOnComplete {
                viewState.hideProgress()
            }

            //подписывает Observer на Observable
            .subscribe({
                viewState.hideProgress()
                viewState.notifyAdapter()
            }, {
                viewState.showErrorMessage(it.message)
                viewState.hideProgress()
                it.printStackTrace()
            })
        )
    }


    //обновляем список
     fun refreshList() {
        viewState.refresh()
        makeList()
    }
}