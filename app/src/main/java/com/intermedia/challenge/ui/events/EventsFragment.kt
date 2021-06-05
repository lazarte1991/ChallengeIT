package com.intermedia.challenge.ui.events

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intermedia.challenge.databinding.FragmentEventsBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel


class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: EventsViewModel by sharedViewModel()
    private val adapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventsList()
    }

    //private fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, text, duration).show() }

    private fun setupEventsList() {


        adapter.onClickListener = { event ->



           // context?.toast(event.title, Toast.LENGTH_LONG)
            // TODO complete
        }
        binding.listEvents.adapter = adapter
        viewModel.events.observe(viewLifecycleOwner, { events ->
            adapter.update(events)
            // TODO fill adapter items
        })
    }
}