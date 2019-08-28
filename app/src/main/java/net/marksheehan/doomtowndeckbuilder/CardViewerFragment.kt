package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.card_list.*
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class CardViewerFragment : Fragment(R.layout.card_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var cards= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cards
        cards = cards.sortedByDescending { it.suit }
        val cardAdapter = CardAdapter(cards)
        card_recycler.adapter = cardAdapter
    }
}