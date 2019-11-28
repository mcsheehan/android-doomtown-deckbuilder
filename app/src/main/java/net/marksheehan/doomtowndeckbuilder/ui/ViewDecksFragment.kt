package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_decks.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.adapters.DescriptiveDeckAdapter
import net.marksheehan.doomtowndeckbuilder.database.entitites.DeckEntity

class ViewDecksFragment : Fragment(R.layout.view_decks){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.view_decks_recycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        val onClickListener : (DeckEntity )-> Unit = {}
        view.view_decks_recycler.adapter = DescriptiveDeckAdapter(onClickListener)
    }
}
