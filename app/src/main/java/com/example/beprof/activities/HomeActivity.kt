package com.example.beprof.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.beprof.R
import com.example.beprof.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.note_items.view.*
import java.util.zip.Inflater
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.homeContainer) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityHomeBinding.inflate(layoutInflater)
       setContentView(binding.root)
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

}