package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.choose_pack_layout.*
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import net.marksheehan.doomtowndeckbuilder.adapters.PackListAdapter
import net.marksheehan.doomtowndeckbuilder.database.PackEntity
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtils
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel

class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout) {

    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtils.providePackRepository(requireContext())
    }

    val checkedTextViewOnClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

        var item: PackEntity = parent.getItemAtPosition(position) as PackEntity
        item.isSelected = !item.isSelected

        viewModel.updateDeck(item)
        view.checkedTextView.isChecked = item.isSelected
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val emptyPackList : List<PackEntity> = emptyList()
        val packListAdapter = PackListAdapter(context!!, emptyPackList)
        viewModel.selectedPacks.observe(this, Observer { it ->
            //            val packListAdapter = PackListAdapter(context!!, it)

            packListAdapter.notifyDataSetChanged()
            pack_list.adapter = packListAdapter
            pack_list.onItemClickListener = checkedTextViewOnClickListener
        })


    }
}