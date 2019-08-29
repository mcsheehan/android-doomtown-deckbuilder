package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_layout.*
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class MainNavHostActivity : AppCompatActivity(R.layout.activity_layout) {

    lateinit var cardViewModel : DoomtownCardsViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.nav_host_fragment)

        cardViewModel = ViewModelProviders.of(this,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(applicationContext))[DoomtownCardsViewModel::class.java]

        //TODO Add drawer layout to the app bar configuration
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId ){
            R.id.choose_packs -> {
                navController.navigate(R.id.action_global_choosePacks)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}