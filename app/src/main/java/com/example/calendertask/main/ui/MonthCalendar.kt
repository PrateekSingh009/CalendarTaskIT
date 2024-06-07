package com.example.calendertask.main.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.model.ItemTask
import com.example.calendertask.utils.extensions.dayOfWeekToInt
import com.example.calendertask.utils.extensions.getDefaultItemDay
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar

class MonthCalendar {

    private fun getFirstDayOfMonth(year: Int, month: Int): Int {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        return firstDayOfMonth.dayOfWeek.toString().dayOfWeekToInt()
    }

    private fun getTotalDaysInMonth(year: Int, month: Int): Int {
        val yearMonth = YearMonth.of(year, month)
        return yearMonth.lengthOfMonth()
    }

    fun createMonthDays(year: Int, month: Int) : List<ItemDayTask> {

        val firstDay : Int = getFirstDayOfMonth(year,month)
        val totalDays : Int = getTotalDaysInMonth(year,month)

        val list = arrayListOf<ItemDayTask>()
        for(i in 1..<firstDay){
            list.add(ItemDayTask("",0,0,0, null))
        }
        for(i in 1..totalDays){
                list.add(ItemDayTask(generateIdBasedOnDate(i,month,year),i,month,year, null))
        }
        return list
    }

     fun generateIdBasedOnDate(day: Int, month:Int, year:Int): String {
//        val calendar = Calendar.getInstance()
        val year = year
        val month = month
        val day = day
//        val hour = calendar.get(Calendar.HOUR_OF_DAY)
//        val minute = calendar.get(Calendar.MINUTE)
//        val second = calendar.get(Calendar.SECOND)
//        val millisecond = calendar.get(Calendar.MILLISECOND)
        return "${year}${String.format("%02d", month)}${String.format("%02d", day)}"
//        return "${year}${String.format("%02d", month)}${String.format("%02d", day)}${String.format("%02d", hour)}${String.format("%02d", minute)}${String.format("%02d", second)}${String.format("%03d", millisecond)}"
    }

    fun mergeTasksByYear(itemDayTasks: List<ItemDayTask>?): List<Pair<Int, List<ItemTask>>>? {
        return itemDayTasks?.groupBy { it.year }
            ?.mapValues { entry ->
                entry.value.flatMap { it.task?.map { task -> ItemTask("${it.year}_${String.format("%02d", it.month)}_${String.format("%02d", it.day)}", task) } ?: emptyList() }
            }
            ?.toList()
            ?.sortedBy { it.first }

    }
}