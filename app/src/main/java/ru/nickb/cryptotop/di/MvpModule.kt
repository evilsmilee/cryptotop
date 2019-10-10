package ru.nickb.cryptotop.di

import dagger.Module
import dagger.Provides
import ru.nickb.cryptotop.mvp.presenter.CurrenciesPresenter
import ru.nickb.cryptotop.mvp.presenter.LatestChartPresenter
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()

}