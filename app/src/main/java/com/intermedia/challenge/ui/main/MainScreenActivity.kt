package com.intermedia.challenge.ui.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ActivityMainScreenBinding

enum class ProviderType{
    BASIC,
    FACEBOOK
}

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.itemIconTintList = null

        //Guardado de datos para la session
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val prefs = getSharedPreferences(getString(R.string.prefs_files), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider",provider)
        prefs.apply()
    }

}