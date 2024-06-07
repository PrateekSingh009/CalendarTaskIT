package com.example.calendertask.main.data

import com.example.calendertask.main.api.ApiService
import com.example.calendertask.main.ui.MonthCalendar
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.model.ItemTask
import com.example.calendertask.utils.extensions.getDefaultItemDay
import javax.inject.Inject

class ListRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getDaysFromRetrofit(year : Int , month : Int) : List<ItemDayTask> {
        val taskList : List<ItemDayTask> = apiService.getTaskList().body() ?: emptyList()
        val dayList : List<ItemDayTask> = MonthCalendar().createMonthDays(year,month)
        val mergedList = dayList.map { item ->
            taskList.find { it.id == item.id } ?: item
        }
       return mergedList
    }

    suspend fun getDayTaskListFromRetrofit(year : Int , month : Int, day: Int) : ItemDayTask {
        return apiService.getDayTaskList(MonthCalendar().generateIdBasedOnDate(day,month,year)).body() ?: getDefaultItemDay(day,month,year)
    }

    suspend fun getMyTaskListFromRetrofit() : List<Pair<Int, List<ItemTask>>>? {
        val itemDayTaskList = apiService.getTaskList().body()
        return MonthCalendar().mergeTasksByYear(itemDayTaskList)
    }

}