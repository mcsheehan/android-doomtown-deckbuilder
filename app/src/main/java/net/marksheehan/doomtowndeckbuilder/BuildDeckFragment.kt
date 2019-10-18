package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.build_deck.*
import kotlinx.android.synthetic.main.choose_pack_layout.view.*
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.viewmodels.BuildDeckViewModel

class BuildDeckFragment : Fragment(R.layout.build_deck) {

    private val viewModel: BuildDeckViewModel by viewModels {
        InjectorUtilities.provideDeckViewerViewModel(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        build_deck_recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        viewModel.allCardsFromSelectedPacks.observe(this, Observer { it ->
            val cardAdapter : CardAdapter = CardAdapter(it)
            build_deck_recycler.adapter = cardAdapter
         })
    }

}
