package com.intermedia.challenge.data.models

import java.io.Serializable

data class Event(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val resourceURI: String = "",
    val urls: List<Url> = listOf(),
    // val modified: Date,
    // val start: Date,
    // val end: Date,
    val thumbnail: Thumbnail = Thumbnail(),
    val comics: Appearances = Appearances(),
    val stories: Appearances = Appearances(),
    val series: Appearances = Appearances(),
    val characters: Character = Character(),
    val creators: Appearances = Appearances(),
    val next: Appearance = Appearance(),
    val previous: Appearance = Appearance()
) : Serializable