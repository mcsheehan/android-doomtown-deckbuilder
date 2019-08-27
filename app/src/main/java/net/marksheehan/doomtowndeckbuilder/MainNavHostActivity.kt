package net.marksheehan.doomtowndeckbuilder

import androidx.navigation.Navigation.findNavController
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_layout.*
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class MainNavHostActivity : AppCompatActivity() {

    lateinit var cardViewModel : DoomtownCardsViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_layout)

        navController = findNavController(R.id.nav_host_fragment)

        cardViewModel = ViewModelProviders.of(this,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(applicationContext))[DoomtownCardsViewModel::class.java]

        //TODO Add drawer layout to the app bar configuration
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)
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