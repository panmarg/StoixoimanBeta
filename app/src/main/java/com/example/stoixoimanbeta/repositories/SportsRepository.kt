package com.example.stoixoimanbeta.repositories

import com.example.stoixoimanbeta.data.Sports
import kotlinx.coroutines.flow.Flow


interface SportsRepository {
    suspend fun getSportsList(): Flow<List<Sports>>
}

