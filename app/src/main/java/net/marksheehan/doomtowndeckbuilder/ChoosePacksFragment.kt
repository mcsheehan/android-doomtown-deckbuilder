package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.choose_pack_layout.*
import net.marksheehan.doomtowndeckbuilder.adapters.NameAndChecked
import net.marksheehan.doomtowndeckbuilder.adapters.PackListAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout){

    val onItemClickListener = AdapterView.OnItemClickListener {
        parent, view, position, id ->
        val item = parent.getItemAtPosition(position)
        println(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val allPacks= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cardPacks

        val cardPacks : List<String> = allPacks.toList()

        val fullListOfCardPacks : MutableList<NameAndChecked> = mutableListOf<NameAndChecked>()
        cardPacks.forEach { fullListOfCardPacks.add(NameAndChecked(it, true))}

        val packListAdapter = PackListAdapter(context!!, fullListOfCardPacks)
//        val simplePackListAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_multiple_choice, cardPacks)

        pack_list.adapter = packListAdapter
        pack_list.onItemClickListener = onItemClickListener
    }
}