package net.marksheehan.doomtowndeckbuilder.navigationhost

import androidx.navigation.Navigation.findNavController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.marksheehan.doomtowndeckbuilder.R

class MainNavHostActivity : AppCompatActivity() {

    override fun onSupportNavigateUp() =
            findNavController(this, R.id.navigation_graph).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_host)
    }
}