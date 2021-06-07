package com.intermedia.challenge.ui.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.databinding.ActivityCharacterDetailsBinding
import com.intermedia.challenge.ui.appearances.AppearancesAdapter
import kotlinx.android.synthetic.main.activity_character_details.*


class CharacterDetails : AppCompatActivity() {
      lateinit var  character : Character
      private val adapter = AppearancesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = intent.getParcelableExtra<Character>("character")
        retrieveCharacter()
        renderUi()
        closed()
        setupRecyclerView()
    }

    private fun retrieveCharacter() {
        character = intent.getParcelableExtra<Character>("character")
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.list_comics)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager?)!!.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun renderUi() {
        var binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.character = character
        binding.listComics.adapter = adapter
        adapter.update(character.comics.appearances)
    }

    private fun closed(){
        btnClosed.setOnClickListener{
            onBackPressed()
        }
    }
}