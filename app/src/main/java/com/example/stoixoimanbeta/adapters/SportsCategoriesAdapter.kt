package com.example.stoixoimanbeta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stoixoimanbeta.data.SportsCategories
import com.example.stoixoimanbeta.databinding.SportsCategoriesItemBinding


class SportsCategoriesAdapter() :
    RecyclerView.Adapter<SportsCategoriesAdapter.SportsCategoriesViewHolder>() {
    private val sportsCategoriesList = mutableListOf<SportsCategories>()
    private val expandedListPosition = arrayListOf<Int>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportsCategoriesAdapter.SportsCategoriesViewHolder {
        return SportsCategoriesViewHolder(
            SportsCategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SportsCategoriesAdapter.SportsCategoriesViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return sportsCategoriesList.size
    }

    fun updateSportsCategories(updatedSportsCategories: List<SportsCategories>) {
        sportsCategoriesList.clear()
        sportsCategoriesList.addAll(updatedSportsCategories)
        //Have all the positions expanded the first time
        expandedListPosition.addAll(updatedSportsCategories.indices)
        notifyDataSetChanged()
    }

    inner class SportsCategoriesViewHolder(private val binding: SportsCategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val sportsCategory = sportsCategoriesList[position]
            binding.tvCategoryTitle.text = sportsCategory.d
            binding.clEvents.isVisible = expandedListPosition.contains(position)
            binding.ivShowEvents.rotation =
                if (expandedListPosition.contains(position)) 180f else 0f

            val eventsAdapter = EventsAdapter(sportsCategory.e)
            binding.rvEvents.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = eventsAdapter
                setHasFixedSize(true)
            }
            binding.ivShowEvents.setOnClickListener {
                if (expandedListPosition.contains(position)) {
                    binding.clEvents.isVisible = false
                    binding.ivShowEvents.rotation = 0f
                    expandedListPosition.remove(position)
                } else {
                    binding.ivShowEvents.rotation = 180f
                    binding.clEvents.isVisible = true
                    expandedListPosition.add(position)
                }
            }
        }
    }


}


