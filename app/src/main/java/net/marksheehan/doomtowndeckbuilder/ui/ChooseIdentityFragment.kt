package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.choose_identity.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.adapters.FullScreenCardAdapter
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.utilities.getSnapPosition
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.ChooseIdentityViewModel

class ChooseIdentityFragment : Fragment(R.layout.choose_identity)
{
    private val viewModel: ChooseIdentityViewModel by viewModels {
        InjectorUtilities.provideChooseIdentityViewModel(requireContext())}

    lateinit var snapHelper : PagerSnapHelper
    lateinit var outfitCardList : List<CardModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(identityChooser)
        createDeckButton.setOnClickListener(onSelectButtonPressed)

        viewModel.filteredData.observe(this, Observer<List<CardModel>> { cardList->
            outfitCardList = cardList
            identityChooser.adapter = FullScreenCardAdapter(outfitCardList)
        })
    }

    private val onSelectButtonPressed = View.OnClickListener { view: View ->
        val position = snapHelper.getSnapPosition(identityChooser)

        if(position == RecyclerView.NO_POSITION){
            Toast.makeText(context,"Select an identity. You may need to choose some packs.",Toast.LENGTH_SHORT).show()
        }
        else {
            val selectedCard = outfitCardList[position]
            Navigation.findNavController(view).navigate(R.id.action_chooseIdentity_to_chooseCards)
        }
    }
}