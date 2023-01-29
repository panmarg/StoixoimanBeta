package com.example.stoixoimanbeta.data

data class SportsCategories(
    val i: String,
    val d: String,
    val e: List<Event>,
    var isExpanded: Boolean = true
)
