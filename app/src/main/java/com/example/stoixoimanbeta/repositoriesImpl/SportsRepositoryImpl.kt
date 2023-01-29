package com.example.stoixoimanbeta.repositoriesImpl

import com.example.stoixoimanbeta.data.Sports
import com.example.stoixoimanbeta.repositories.SportsRepository
import com.example.stoixoimanbeta.service.SportsApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(private val sportsApiInterface: SportsApiInterface) :
    SportsRepository {
    override suspend fun getSportsList(): Flow<List<Sports>> {
        return flow {
            try {
                val response = sportsApiInterface.getSports()
                if (response.isSuccessful) {
                    response.body()?.let { emit(it) }
                } else {
                    emit(listOf())
                }
            } catch (Ex: Exception) {
                emit(listOf())
            }
        }
    }
}