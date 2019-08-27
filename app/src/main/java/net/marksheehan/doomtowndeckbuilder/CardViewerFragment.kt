package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.card_list.*
import net.marksheehan.doomtowndeckbuilder.adapters.CardAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class CardViewerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val cards= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cards

        val cardAdapter = CardAdapter(cards)
        card_recycler.adapter = cardAdapter
    }
}