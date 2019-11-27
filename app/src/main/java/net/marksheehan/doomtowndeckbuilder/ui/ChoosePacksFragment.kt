package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.choose_pack_layout.view.*
import net.marksheehan.doomtowndeckbuilder.adapters.PackListAdapter
import net.marksheehan.doomtowndeckbuilder.database.entitites.PackEntity
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.ChoosePacksViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import net.marksheehan.doomtowndeckbuilder.R


class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout) {

    private val viewModel: ChoosePacksViewModel by viewModels {
        InjectorUtilities.providePackChooserViewModel(requireContext())
    }

    private val packClickListener : (PackEntity) -> Unit =  { packEntity -> viewModel.updatePack(packEntity)}
    private val packListAdapter : PackListAdapter = PackListAdapter(packClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.pack_list_recycler_view.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        view.pack_list_recycler_view.adapter = packListAdapter

        val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        view.pack_list_recycler_view.addItemDecoration(itemDecoration)

        viewModel.livePacks.observe(this, Observer<List<PackEntity>> { packList ->
            packListAdapter.submitList(packList)
            packListAdapter.notifyDataSetChanged()
        })
    }
}