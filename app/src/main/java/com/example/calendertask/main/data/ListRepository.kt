package com.example.calendertask.main.data

import com.example.calendertask.main.api.ApiService
import com.example.calendertask.main.ui.MonthCalendar
import com.example.calendertask.model.ItemDayTask
import javax.inject.Inject

class ListRepository @Inject constructor(private val apiService: ApiService) {

    fun getDaysFromRetrofit(year : Int , month : Int) : List<ItemDayTask>? {
       return MonthCalendar().createMonthDays(year,month)
    }

    fun getTaskListFromRetrofit(year : Int , month : Int, days: Int) : ItemDayTask {
        return ItemDayTask(0,days,month,year, listOf("Pick Ben","Do HomeWork"))
    }

    fun getMyTaskListFromRetrofit() : List<ItemDayTask> {
        return listOf(ItemDayTask(0,4,5,2024, listOf("Pick Ben","Do HomeWork")))
    }

}