package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.choose_pack_layout.*
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import net.marksheehan.doomtowndeckbuilder.adapters.TextAndBoolean
import net.marksheehan.doomtowndeckbuilder.adapters.PackListAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout) {

    lateinit var doomtownCardsViewModel : DoomtownCardsViewModel

    val checkedTextViewOnClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item: TextAndBoolean = parent.getItemAtPosition(position) as TextAndBoolean
        item.checked = !item.checked
        view.checkedTextView.isChecked = item.checked

        this.doomtownCardsViewModel.selectedCardPacks.value!![item.name] = item.checked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        doomtownCardsViewModel = ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java]

        doomtownCardsViewModel.selectedCardPacks.observe(this, Observer { item ->  })

        val mutableMap = doomtownCardsViewModel.selectedCardPacks.value!!

        val fullListOfCardPacks: MutableList<TextAndBoolean> = mutableListOf()
        mutableMap.forEach { (key, value) -> fullListOfCardPacks.add(TextAndBoolean(key, value)) }

        val packListAdapter = PackListAdapter(context!!, fullListOfCardPacks)

        pack_list.adapter = packListAdapter
        pack_list.onItemClickListener = checkedTextViewOnClickListener
    }
}