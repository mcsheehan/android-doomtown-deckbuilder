package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.travijuu.numberpicker.library.Enums.ActionEnum
import com.travijuu.numberpicker.library.Interface.ValueChangedListener
import kotlinx.android.synthetic.main.build_deck.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.adapters.DescriptiveCardAdapter
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModelAndNumberSelected
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.BuildDeckViewModel

class BuildDeckFragment : Fragment(R.layout.build_deck) {

    private val viewModel: BuildDeckViewModel by viewModels {
        InjectorUtilities.provideDeckViewerViewModel(requireContext())
    }

    private lateinit var adapter : DescriptiveCardAdapter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save -> {return true}
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_button, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val args: BuildDeckFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        val deckEntity = args.deck
        deckEntity.deckname

        build_deck_recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val onCardSelected : (CardModelAndNumberSelected) -> Unit = {
            val navDirection = BuildDeckFragmentDirections.actionBuildDeckToIndividualCardView(it.cardModel)
            Navigation.findNavController(view).navigate(navDirection)
        }

        val spinnerUpdated : (CardModelAndNumberSelected) -> Unit = {
            // Update the card to have the new number of cards specified.
            
        }

        adapter = DescriptiveCardAdapter(onCardSelected, spinnerUpdated)
        build_deck_recycler.adapter = adapter

        viewModel.allCardsFromSelectedPacks.observe(viewLifecycleOwner, Observer{
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

}
