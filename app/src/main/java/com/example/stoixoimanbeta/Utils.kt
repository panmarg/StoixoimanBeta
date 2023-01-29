package com.example.stoixoimanbeta

fun String.getHomeTeam(): String {
    return this.substringBefore("-")
}

fun String.getAwayTeam(): String {
    return this.substringAfter("-")
}





