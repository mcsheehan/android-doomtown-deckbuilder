package net.marksheehan.doomtowndeckbuilder

import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.mark.doomtowndeckbuilder.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_list.*

class ActualMain : AppCompatActivity() {


    val fabClick = View.OnClickListener { view: View ->
        Snackbar.make(view, R.string.downloading_card_data, Snackbar.LENGTH_LONG).show()
    }
    override fun onSupportNavigateUp() =
            findNavController(this, R.id.navigation_graph).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host = NavHostFragment.create(R.navigation.navigation_graph)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, host).setPrimaryNavigationFragment(host).commit()

//        val navController = NavHostFragment.findNavController(R.id.navigation_graph)
//        NavigationUI.setupActionBarWithNavController(navController)

        fab.setOnClickListener(fabClick)

        val doomtownDbAccess = DoomtownDbAccess()
        doomtownDbAccess.sendServerRequestCardList(cardResult)
    }
}