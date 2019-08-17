package net.marksheehan.doomtowndeckbuilder

import androidx.navigation.Navigation.findNavController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainNavHostActivity : AppCompatActivity() {

    override fun onSupportNavigateUp() =
            findNavController(this, R.id.navigation_graph).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newlayout)
    }
}