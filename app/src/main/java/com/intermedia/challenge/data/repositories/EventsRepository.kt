package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.services.EventService
import com.intermedia.challenge.data.services.EventsResponse

class EventsRepository (
    private val eventService: EventService
    ): BaseRepository(){
        suspend fun getEvents(offset: Int, limit: Int = 25): NetResult<EventsResponse> =
            handleResult(eventService.getEvents(authParams.getMap(), offset, limit))
}