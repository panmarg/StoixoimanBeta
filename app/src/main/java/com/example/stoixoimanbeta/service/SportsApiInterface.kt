package com.example.stoixoimanbeta.service

import com.example.stoixoimanbeta.data.Sports
import retrofit2.Response
import retrofit2.http.GET

interface SportsApiInterface {
    @GET("sports")
    suspend fun getSports(): Response<List<Sports>>
}

