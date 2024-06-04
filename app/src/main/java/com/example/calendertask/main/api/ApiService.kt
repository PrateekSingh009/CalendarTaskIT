package com.example.calendertask.main.api

import com.example.calendertask.model.ItemDayTask
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/api/getCalendarTaskLists")
    suspend fun getTaskList(
        @Query("user_id") id: Int = 123
    ) : Response<List<ItemDayTask>>

    @POST("/api/storeCalendarTask")
    suspend fun postNewTask(
        @Query("user_id") id: Int = 123,
        @Query("task") task : ItemDayTask
    )

    @GET("/api/deleteCalendarTask")
    suspend fun deleteTask(
        @Query("user_id") id: Int = 123,
        @Query("task_id") task_id : Int
    )

}