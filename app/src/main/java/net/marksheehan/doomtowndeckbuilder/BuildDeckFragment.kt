package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class BuildDeckFragment : Fragment(R.layout.choose_cards) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cards= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cards
    }

}
