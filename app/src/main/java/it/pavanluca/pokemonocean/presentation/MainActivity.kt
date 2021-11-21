package it.pavanluca.pokemonocean.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.databinding.ActivityMainBinding
import it.pavanluca.pokemonocean.presentation.pokemon.detail.DetailFragment
import it.pavanluca.pokemonocean.presentation.pokemon.home.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val APP_BAR_EXTENDED_KEY = "app_bar_extended_key"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var appBarIsExtended: Boolean? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        savedInstanceState?.let {
            if (it.containsKey(APP_BAR_EXTENDED_KEY)) {
                appBarIsExtended = it.getBoolean(APP_BAR_EXTENDED_KEY)
            }
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, dest, _ ->
            if ((dest as FragmentNavigator.Destination).className == DetailFragment::class.java.name) {
                binding.appBar.setExpanded(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            } else {
                appBarIsExtended?.let {
                    binding.appBar.setExpanded(it)
                }
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        appBarIsExtended?.let {
            outState.putBoolean(APP_BAR_EXTENDED_KEY, it)
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    fun showDialogError(errorMessage: Int, onRetry: () -> Unit) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.error_title)
            .setMessage(errorMessage)
            .setPositiveButton(R.string.action_retry) { dialog, _ ->
                onRetry()
                dialog?.dismiss()
            }
            .create()
            .show()
    }
}