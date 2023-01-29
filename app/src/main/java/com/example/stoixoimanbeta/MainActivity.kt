package com.example.stoixoimanbeta

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stoixoimanbeta.adapters.SportsCategoriesAdapter
import com.example.stoixoimanbeta.databinding.ActivityMainBinding
import com.example.stoixoimanbeta.viewmodels.SportsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sportsViewModel: SportsViewModel by viewModels()
    private val sportsCategoriesAdapter: SportsCategoriesAdapter = SportsCategoriesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvSports.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sportsCategoriesAdapter
            setHasFixedSize(true)
        }

        sportsViewModel.sportsByCategories.observe(this){
            sportsCategoriesAdapter.updateSportsCategories(it)
        }


    }
}