package it.pavanluca.pokemonocean.presentation.pokemon

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import it.pavanluca.pokemonocean.R

class MainActivity : AppCompatActivity() {

    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}