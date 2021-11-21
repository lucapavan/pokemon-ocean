package it.pavanluca.pokemonocean.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.databinding.ActivityMainBinding
import it.pavanluca.pokemonocean.di.PokemonFragmentFactory
import it.pavanluca.pokemonocean.presentation.pokemon.detail.DetailFragment
import it.pavanluca.pokemonocean.presentation.pokemon.home.HomeFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var appBarIsExtended = true

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setupToolbar()
    }

    private fun setupToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, dest, _ ->
            if ((dest as FragmentNavigator.Destination).className == DetailFragment::class.java.name) {
                binding.appBar.setExpanded(true)
            } else {
                binding.appBar.setExpanded(appBarIsExtended)
            }
        }

        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if ((navController.currentDestination as FragmentNavigator.Destination).className == HomeFragment::class.java.name) {
                appBarIsExtended = verticalOffset == 0
            }
        })

        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.collapsingToolbarLayout.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
    }
}