package com.intermedia.challenge.ui.events

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.EventsRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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

                    //Trae 15 eventos futuros (en la api hay solo uno a partir de la fecha de hoy)
                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
                    var limit = 0
                    val totalEvents = response.data.eventsList.events
                    for (i in totalEvents){
                        if (i.start != null && i.start >= date && limit <= 15){
                            list.add(i)
                            limit ++
                        }
                    }

                    //Logica para traer todos los eventos sin importar la fecha
                    //list.addAll(response.data.eventsList.events.sortedBy { it.start })

                    _events.postValue(list.sortedBy { it.start })

                }
                is NetResult.Error -> {
                    Log.e(response.toString(),"ERROR")
                }
            }
        }
    }
}