package com.intermedia.challenge.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val resourceURI: String = "",
    val urls: List<Url> = listOf(),
    val modified: String,
    var start: String,
    val end: String,
    val thumbnail: Thumbnail = Thumbnail(),
    val comics: Appearances = Appearances(),
    val stories: Appearances = Appearances(),
    val series: Appearances = Appearances(),
    val characters: Character = Character(),
    val creators: Appearances = Appearances(),
    val next: Appearance = Appearance(),
    val previous: Appearance = Appearance()
) : Parcelable
