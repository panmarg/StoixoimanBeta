package com.example.stoixoimanbeta.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stoixoimanbeta.data.Sports
import com.example.stoixoimanbeta.data.SportsCategories
import com.example.stoixoimanbeta.repositories.SportsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val sportsRepository: SportsRepository
) : ViewModel() {

    private var sportsList = listOf<Sports>()

    private val _sportsByCategories = MutableLiveData<List<SportsCategories>>(listOf())
    val sportsByCategories: LiveData<List<SportsCategories>>
        get() = _sportsByCategories


    init {
        viewModelScope.launch {
            loadSports()
            viewModelScope.launch {
                loadSportsByCategories()
            }
        }
    }

    private fun loadSportsByCategories() {
        _sportsByCategories.postValue(sportsList.map {
            SportsCategories(it.i, it.d as String, it.e)
        })
    }


    private suspend fun loadSports() {
        sportsRepository.getSportsList().collect { result ->
            if (result.isNotEmpty()) {
                sportsList = result.filter { it.d is String }
            }
        }
    }

}



