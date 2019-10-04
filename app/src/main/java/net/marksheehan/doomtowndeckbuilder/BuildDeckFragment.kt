package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel

class BuildDeckFragment : Fragment(R.layout.choose_cards) {

    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtilities.provideCardViewerViewModel(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}
