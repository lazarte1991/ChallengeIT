package com.intermedia.challenge.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters
    private val list = mutableListOf<Character>()

    var offset = 0

    init {
        loadCharacters(offset)
    }

    private fun loadCharacters(offset: Int) {
        viewModelScope.launch {
            when (val response = charactersRepository.getCharacters(offset)) {

                is NetResult.Success -> {
                    list.addAll(response.data.charactersList.characters)
                    _characters.postValue(list)
                }
                is NetResult.Error -> {
                    Log.e(response.toString(),"ERROR")
                }
            }
        }
    }

    fun loadMoreCharacters() {
       offset += 15
       loadCharacters(offset)
    }
}