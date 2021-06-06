package com.intermedia.challenge.ui.events

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.databinding.ViewEventItemBinding
import com.intermedia.challenge.ui.appearances.AppearancesAdapter
import com.intermedia.challenge.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.view_event_item.*
import kotlinx.android.synthetic.main.view_event_item.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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
           // Log.e(item.start,"FECHA:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")


            if(item.start != null) {
                item.start = item.start.substring(0,10)
            }

            list_comics.adapter = AppearancesAdapter()

            (list_comics.adapter as AppearancesAdapter).update(item.comics.appearances)

            binding.button2.setOnClickListener {

                    if(comicLayout.visibility == View.GONE){
                        comicLayout.visibility = View.VISIBLE
                        binding.button2.setImageResource(R.mipmap.ic_up_icon)
                    }else{
                        comicLayout.visibility = View.GONE
                        binding.button2.setImageResource(R.mipmap.ic_down_icon)
                    }

            }
        }
    }
}





