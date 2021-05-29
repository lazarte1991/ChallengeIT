package com.intermedia.challenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.databinding.ViewEventItemBinding

class EventsAdapter : BaseAdapter<Event, EventsAdapter.EventsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            ViewEventItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_event_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class EventsViewHolder(
        private val binding: ViewEventItemBinding,
        private val onClickListener: ((Event) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event) = with(itemView) {
            binding.event = item
            binding.root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }
}