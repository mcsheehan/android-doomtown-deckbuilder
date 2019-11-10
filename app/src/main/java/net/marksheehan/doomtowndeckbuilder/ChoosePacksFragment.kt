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
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.viewmodels.PackChooserViewModel
import androidx.recyclerview.widget.DividerItemDecoration


class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout) {

    private val viewModel: PackChooserViewModel by viewModels {
        InjectorUtilities.providePackChooserViewModel(requireContext())
    }

    private lateinit var packListAdapter : PackListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        packListAdapter = PackListAdapter { it -> viewModel.updatePack(it)}

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