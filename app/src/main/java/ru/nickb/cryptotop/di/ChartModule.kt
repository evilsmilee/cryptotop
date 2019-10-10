package ru.nickb.cryptotop.di

import dagger.Module
import dagger.Provides
import ru.nickb.cryptotop.YearValueFormatter
import ru.nickb.cryptotop.chart.LatestChart
import javax.inject.Singleton

@Module
class ChartModule {


    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()



    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()

}