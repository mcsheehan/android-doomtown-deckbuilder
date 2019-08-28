package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class ChoosePacksFragment : Fragment(R.layout.choose_packs){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cards= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cards

        var allPackSet : Set<String> = emptySet()

        cards.forEach { allPackSet = allPackSet + it.pack }

        val cardAdapter = CardAdapter(cards)
    }
}