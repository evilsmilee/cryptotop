package ru.nickb.cryptotop.mvp.view

import com.arellomobile.mvp.MvpView

interface LatestChartView: BaseView {

    fun addEntryToChart(value: Float, date: String = "")
    fun addEntryToChart(date: Float, value: Float)
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(error: String?)
    fun refresh()

}