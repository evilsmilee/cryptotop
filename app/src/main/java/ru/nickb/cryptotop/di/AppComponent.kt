package ru.nickb.cryptotop.di

import dagger.Component
import ru.nickb.cryptotop.activities.ChartActivity
import ru.nickb.cryptotop.activities.MainActivity
import ru.nickb.cryptotop.chart.LatestChart
import ru.nickb.cryptotop.fragments.CurrenciesListFragment
import ru.nickb.cryptotop.mvp.presenter.CurrenciesPresenter
import ru.nickb.cryptotop.mvp.presenter.LatestChartPresenter

import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
    fun inject(chart: LatestChart)
    fun inject(chartActivity: ChartActivity)
}