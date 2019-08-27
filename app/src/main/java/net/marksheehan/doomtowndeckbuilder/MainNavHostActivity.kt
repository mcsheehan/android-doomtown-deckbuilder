package net.marksheehan.doomtowndeckbuilder

import androidx.navigation.Navigation.findNavController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class MainNavHostActivity : AppCompatActivity() {

    override fun onSupportNavigateUp() =
            findNavController(this, R.id.navigation_graph).navigateUp()

    lateinit var cardViewModel : DoomtownCardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cardViewModel = ViewModelProviders.of(this,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(applicationContext))[DoomtownCardsViewModel::class.java]

        setContentView(R.layout.navigation_host)
    }
}