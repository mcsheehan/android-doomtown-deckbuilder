package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class ChooseCardsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_cards, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cards= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cards
    }

}
