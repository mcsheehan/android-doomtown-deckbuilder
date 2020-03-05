package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_decks.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.adapters.DescriptiveDeckAdapter
import net.marksheehan.doomtowndeckbuilder.database.CardAndDeck
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.ViewDecksViewModel

class CurrentDecksFragment : Fragment(R.layout.view_decks){

    private val viewModel: ViewDecksViewModel by viewModels {
        InjectorUtilities.provideViewDecksViewModel(requireContext())}

    lateinit var adapter : DescriptiveDeckAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.view_decks_recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        view.createDeckFab.setOnClickListener{
            val navDir = CurrentDecksFragmentDirections.actionViewCreatedDecksToChooseIdentity()
            Navigation.findNavController(view).navigate(navDir)
        }

        val onClickListener : (CardAndDeck)-> Unit = {
            val navDir = CurrentDecksFragmentDirections.actionViewCreatedDecksToBuildDeck(it.deckEntity)
            Navigation.findNavController(view).navigate(navDir)
        }
        adapter = DescriptiveDeckAdapter(onClickListener)
        view.view_decks_recycler.adapter = adapter

        viewModel.cardsAndDecks.observe(viewLifecycleOwner, Observer<List<CardAndDeck>> {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }
}
