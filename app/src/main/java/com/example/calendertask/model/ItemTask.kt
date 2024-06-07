package com.example.calendertask.model

data class ItemTask(
    val date: String,
    val task: String?
) {
    constructor() : this("", null)
}
