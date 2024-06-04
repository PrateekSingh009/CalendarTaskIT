package com.example.calendertask.model

data class ItemDayTask(
    val id : Int,
    val day : Int,
    val month : Int,
    val year : Int,
    val task : List<String>?
)
{
    constructor() : this(0,0,0,0,null)
}