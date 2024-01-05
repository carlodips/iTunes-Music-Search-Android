package dev.carlodips.itunesmusicsearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.carlodips.itunesmusicsearch.R
import dev.carlodips.itunesmusicsearch.databinding.ActivityMainBinding
import dev.carlodips.itunesmusicsearch.ui.theme.ITunesMusicSearchKotlinTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appbar)
        supportActionBar?.elevation = 2f
        setStartingNavGraph()

        supportActionBar?.title = applicationContext.getString(R.string.app_name)
    }

    private fun setStartingNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.mainContent.id) as NavHostFragment
        val navController = navHostFragment.navController

        navController.graph = navController.navInflater.inflate(R.navigation.nav_root)
    }
}
