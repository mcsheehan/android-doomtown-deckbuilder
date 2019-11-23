package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.build_deck.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.adapters.DescriptiveCardAdapter
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.BuildDeckViewModel

class BuildDeckFragment : Fragment(R.layout.build_deck) {

    private val viewModel: BuildDeckViewModel by viewModels {
        InjectorUtilities.provideDeckViewerViewModel(requireContext())
    }

    private lateinit var adapter : DescriptiveCardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        build_deck_recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)


        val clickListener : (CardModel) -> Unit = {

        }
        adapter = DescriptiveCardAdapter(clickListener)
        build_deck_recycler.adapter = adapter


        viewModel.allCardsFromSelectedPacks.observe(this, Observer{
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

    }

}
