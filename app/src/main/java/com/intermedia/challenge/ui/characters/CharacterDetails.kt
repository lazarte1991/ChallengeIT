package com.intermedia.challenge.ui.characters

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.bumptech.glide.Glide
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Appearance
import com.intermedia.challenge.data.models.Appearances
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.databinding.ActivityCharacterDetailsBinding
import kotlinx.android.synthetic.main.activity_character_details.*


class CharacterDetails : AppCompatActivity() {
      lateinit var  character : Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        retrieveCharacter()
        renderUi()
        closed()
    }

    //



    private fun retrieveCharacter() {
    character = intent.getParcelableExtra<Character>("character")

     //context?.toast(character.toString(), Toast.LENGTH_LONG)
     // Log.e(intent.toString(),"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    }

    private fun renderUi() {
        var binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.character = character

        val arrayAdapter : ArrayAdapter<String>
        val comics = mutableListOf(character.comics)
        val listaComics = findViewById<ListView>(R.id.lista_comics)

        val arrayString : ArrayList<String> = ArrayList()

        for(i in comics[0].appearances) {
            arrayString.add(i.name)
        }

        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayString)

        listaComics.adapter = arrayAdapter

    }

    private fun closed(){
        btnClosed.setOnClickListener{
            onBackPressed()
        }
    }



/*
  //
  //val imgView = findViewById<ImageView>(R.id.detailsImage)
  //val linearLayout = findViewById<LinearLayout>(R.id.detailsImage)
  //val imageView = ImageView(this)
  //Glide.with(this).load(img).into(detailsImage)

  //detailsName.text = character.name
  //detailsDescription.text = character?.description
   img.let { mCover ->
              detailsImage.LoadImage(mCover)
}*/
}