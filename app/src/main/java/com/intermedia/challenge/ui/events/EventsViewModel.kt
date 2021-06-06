package com.intermedia.challenge.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.EventsRepository
import kotlinx.coroutines.launch

class EventsViewModel (private val eventsRepository: EventsRepository) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events
    private val list = mutableListOf<Event>()

    init {
        loadEvents(0)
    }

    private fun loadEvents(offset: Int) {
        viewModelScope.launch {
            when (val response = eventsRepository.getEvents(offset)) {
                is NetResult.Success -> {

                    list.addAll(response.data.eventsList.events.sortedByDescending { it.start })
                    _events.postValue(list.sortedBy { it.start })

                }
                is NetResult.Error -> {
                    // TODO complete
                }
            }
        }
    }

    fun loadMoreEvents() {
        // TODO complete
    }
}