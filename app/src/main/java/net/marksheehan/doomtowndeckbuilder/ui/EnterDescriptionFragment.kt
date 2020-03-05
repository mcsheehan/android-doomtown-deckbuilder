package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.ChooseIdentityViewModel
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities

class EnterDescriptionFragment : Fragment(R.layout.enter_description_layout){
    private val viewModel: ChooseIdentityViewModel by viewModels {
        InjectorUtilities.provideChooseIdentityViewModel(requireContext())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}