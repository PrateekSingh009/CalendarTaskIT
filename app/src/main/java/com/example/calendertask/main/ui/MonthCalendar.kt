package com.example.calendertask.main.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.utils.extensions.dayOfWeekToInt
import java.time.LocalDate
import java.time.YearMonth

class MonthCalendar {

    fun getFirstDayOfMonth(year: Int, month: Int): Int {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        return firstDayOfMonth.dayOfWeek.toString().dayOfWeekToInt()
    }

    fun getTotalDaysInMonth(year: Int, month: Int): Int {
        val yearMonth = YearMonth.of(year, month)
        return yearMonth.lengthOfMonth()
    }

    fun createMonthDays(year: Int, month: Int) : List<ItemDayTask> {

        val firstDay : Int = getFirstDayOfMonth(year,month)
        val totalDays : Int = getTotalDaysInMonth(year,month)

        val list = arrayListOf<ItemDayTask>()
        for(i in 1..<firstDay){
            list.add(ItemDayTask(0,0,0,0, null))
        }
        for(i in 1..totalDays){
            list.add(ItemDayTask(0,i,month,year, null))
        }
        return list
    }
}