package net.marksheehan.doomtowndeckbuilder

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
import net.marksheehan.doomtowndeckbuilder.adapters.FullScreenCardAdapter
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtilities
import net.marksheehan.doomtowndeckbuilder.utilities.getSnapPosition
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel

class ChooseIdentityFragment : Fragment(R.layout.choose_identity)
{
    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtilities.provideCardViewerViewModel(requireContext())
    }

    lateinit var snapHelper : PagerSnapHelper
    lateinit var outfitCardList : List<CardModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(identityChooser)
        selectButton.setOnClickListener(onSelectButtonPressed)

        viewModel.allCardsFromSelectedPacks.observe(this, Observer<List<CardModel>> { cardList->
            outfitCardList = cardList.filter { it.type == "Outfit"}.sortedByDescending { it.gang }
            identityChooser.adapter = FullScreenCardAdapter(outfitCardList)
        })
    }

    val onSelectButtonPressed = View.OnClickListener { view: View ->

        val position = snapHelper.getSnapPosition(identityChooser)

        if(position != RecyclerView.NO_POSITION){
            val selectedCard = outfitCardList[position]
            val cardId = selectedCard.cardId
            Navigation.findNavController(view).navigate(R.id.action_chooseIdentity_to_chooseCards)
        }
        else{
            Toast.makeText(context,"Select an identity. You may need to choose some packs.",Toast.LENGTH_SHORT).show()
        }
    }
}