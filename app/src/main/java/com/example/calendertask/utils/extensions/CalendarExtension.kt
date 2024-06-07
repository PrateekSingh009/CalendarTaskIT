package com.example.calendertask.utils.extensions


fun Int.monthToString() : String {
    return when(this) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> ""
    }
}

fun Int.monthToTrimmedString() : String {
    return when(this) {
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Apr"
        5 -> "May"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Aug"
        9 -> "Sept"
        10 -> "Oct"
        11 -> "Nov"
        12 -> "Dec"
        else -> ""
    }
}

fun String.dayOfWeekToInt() : Int {
    return when(this) {
        "SUNDAY" -> 7
        "MONDAY" -> 1
        "TUESDAY" -> 2
        "WEDNESDAY" -> 3
        "THURSDAY" -> 4
        "FRIDAY" -> 5
        "SATURDAY" -> 6
        else -> 0
    }
}

fun String.dateSeparator() : List<Int> = this.split("_").map { it.toInt() }

fun dateToString(year : Int, month : Int, day : Int) : String{
    return month.monthToString() + ", " + day + " " + year
}
