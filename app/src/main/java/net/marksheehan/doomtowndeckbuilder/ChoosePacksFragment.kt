package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.choose_pack_layout.view.*
import net.marksheehan.doomtowndeckbuilder.adapters.PackListAdapter
import net.marksheehan.doomtowndeckbuilder.database.PackEntity
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtils
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel
import net.marksheehan.doomtowndeckbuilder.adapters.PackEntityClicked


class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout) {

    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtils.providePackRepository(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val packClickedCallback = object : PackEntityClicked {
            override fun clicked(packEntity: PackEntity) {
                packEntity.isSelected = !packEntity.isSelected
                viewModel.updatePack(packEntity)
            }
        }

        val packListAdapter = PackListAdapter( packClickedCallback )

        view.pack_list_recycler_view.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        view.pack_list_recycler_view.adapter = packListAdapter

        viewModel.selectedPacks.observe(this, Observer<List<PackEntity>> { packList ->
            packListAdapter.submitList(packList)
        })
    }
}