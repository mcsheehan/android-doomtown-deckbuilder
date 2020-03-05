package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

import kotlinx.android.synthetic.main.card_grid_view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.CardViewerViewModel

class CardViewerFragment : Fragment(R.layout.card_grid_view) {

    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtilities.provideCardViewerViewModel(requireContext())
    }

    lateinit var cardAdapter: CardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val clickListener: (CardModel) -> Unit = {currentCard ->
            val bundle = Bundle()
            bundle.putParcelable(null, currentCard)
            Navigation.findNavController(view).navigate(R.id.action_cardViewerFragment_to_individualCardViewer, bundle)}

        cardAdapter = CardAdapter(clickListener)
        card_recycler.adapter = cardAdapter

        viewModel.cardsFromSelectedPacksSorted.observe(viewLifecycleOwner, Observer { cards ->
            cardAdapter.submitList(cards)
            cardAdapter.notifyDataSetChanged()
        })
    }
}