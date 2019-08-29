package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.card_grid_view.*
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class CardViewerFragment : Fragment(R.layout.card_grid_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val viewModel = ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java]

        var cards = viewModel.cards
        cards = cards.sortedByDescending { it.suit }

        val cardAdapter = CardAdapter(cards)
        card_recycler.adapter = cardAdapter

        viewModel.selectedCardPacks.observe(this, Observer { it: MutableMap<String, Boolean> ->
            cards = viewModel.filterCardsBySelectedPacks()
            val filteredCardAdapter = CardAdapter(cards)
            card_recycler.adapter = filteredCardAdapter
        })
    }
}