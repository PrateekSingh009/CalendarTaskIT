package com.example.calendertask.model

import com.google.gson.annotations.SerializedName

data class ItemDayTask(
    @SerializedName("id")
    val id : String,
    @SerializedName("day")
    val day : Int,
    @SerializedName("month")
    val month : Int,
    @SerializedName("year")
    val year : Int,
    @SerializedName("task")
    val task : List<String>?
)
{
    constructor() : this("",0,0,0,null)
}