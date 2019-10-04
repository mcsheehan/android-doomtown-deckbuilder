package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import kotlinx.android.synthetic.main.card_grid_view.*
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel

class CardViewerFragment : Fragment(R.layout.card_grid_view) {

    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtilities.provideCardViewerViewModel(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.allCardsFromSelectedPacks.observe(this, Observer { it ->
            val cards = it.sortedByDescending { it.suit }
            val filteredCardAdapter = CardAdapter(it)
            card_recycler.adapter = filteredCardAdapter
        })
    }
}