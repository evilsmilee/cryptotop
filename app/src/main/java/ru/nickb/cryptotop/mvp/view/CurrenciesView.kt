package ru.nickb.cryptotop.mvp.view

import ru.nickb.cryptotop.adapter.CurrenciesAdapter

interface CurrenciesView: BaseView {

    fun addCurrency(currency: CurrenciesAdapter.Currency)
    fun notifyAdapter()
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(error: String?)
    fun refresh()

}