package com.example.stoixoimanbeta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stoixoimanbeta.R
import com.example.stoixoimanbeta.data.Event
import com.example.stoixoimanbeta.databinding.EventsItemBinding
import com.example.stoixoimanbeta.getAwayTeam
import com.example.stoixoimanbeta.getHomeTeam
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


class EventsAdapter(eventsList: List<Event>) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    private var _eventsList: MutableList<Event> = eventsList as MutableList<Event>


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.EventsViewHolder {
        return EventsViewHolder(
            EventsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: EventsAdapter.EventsViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return _eventsList.size
    }

    private fun sortEventsByFavorites() {
        _eventsList.sortByDescending { it.isFavorite }
        notifyDataSetChanged()
    }

    private fun countDownFlow(duration: Long, interval: Long): Flow<Long> = flow {
        var time = duration
        while (time > 0) {
            emit(time)
            delay(interval)
            time -= interval
        }
    }

    inner class EventsViewHolder(private val binding: EventsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val event = _eventsList[position]
            val scope = CoroutineScope(Dispatchers.Main)
            val timer = countDownFlow(event.tt, 1000)
            scope.launch {
                timer.collect({ time ->
                    binding.tvEventTime.text = "Event starts in : " + time
                })
            }
            binding.tvHomeTeam.text = event.d.getHomeTeam()
            binding.tvAwayTeam.text = event.d.getAwayTeam()
            if (event.isFavorite) {
                binding.ivFavoriteEvent.setBackgroundResource(R.drawable.star_filled)
            } else {
                binding.ivFavoriteEvent.setBackgroundResource(R.drawable.star_outline)
            }
            binding.ivFavoriteEvent.setOnClickListener {
                event.isFavorite = !event.isFavorite
                if (event.isFavorite) {
                    binding.ivFavoriteEvent.setBackgroundResource(R.drawable.star_filled)
                } else {
                    binding.ivFavoriteEvent.setBackgroundResource(R.drawable.star_outline)
                }
                sortEventsByFavorites()
            }
        }
    }


}