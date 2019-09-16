package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import kotlinx.android.synthetic.main.card_grid_view.*
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.database.MainDatabase
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtils
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel

class CardViewerFragment : Fragment(R.layout.card_grid_view) {

    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtils.providePackRepository(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.allCards.observe(this, Observer { it ->
            val cards = it.sortedByDescending { it.suit }
            val filteredCardAdapter = CardAdapter(it)
            card_recycler.adapter = filteredCardAdapter
        })

//        viewModel.allCards.observe(viewLifecycleOwner){test -> card_recycler.adapter.su}
//        val cards = viewModel.allCards
//        var currentcards = cards.value!!
//        cards = cards.sortedByDescending { it.suit }

//        viewModel.selectedCardPacks.observe(this, Observer { it: MutableMap<String, Boolean> ->
//            cards = viewModel.filterCardsBySelectedPacks()
//            val filteredCardAdapter = CardAdapter(cards)
//            card_recycler.adapter = filteredCardAdapter
//        })
    }
}