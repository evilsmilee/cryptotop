package ru.nickb.cryptotop

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*

class YearValueFormatter : ValueFormatter() {



    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = value.toLong()
        return calendar.toFormatted()
    }

    fun Calendar.toFormatted(): String {
        val date = this.timeInMillis
        return date.dateToString("MMM yyyy")
    }
}