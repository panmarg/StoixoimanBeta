package com.example.stoixoimanbeta.data

data class Event(
    val d: String,
    val i: Int,
    val si: String,
    val sh: String,
    val tt: Long,
    var isFavorite: Boolean = false
)
