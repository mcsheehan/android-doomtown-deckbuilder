package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.choose_identity.*
import net.marksheehan.doomtowndeckbuilder.adapters.FullScreenCardAdapter
import net.marksheehan.doomtowndeckbuilder.database.PackEntity
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.utilities.InjectorUtils
import net.marksheehan.doomtowndeckbuilder.viewmodels.CardViewerViewModel

class ChooseIdentityFragment : Fragment(R.layout.choose_identity)
{
    private val viewModel: CardViewerViewModel by viewModels {
        InjectorUtils.provideCardViewerViewModel(requireContext())
    }

    lateinit var snapHelper : LinearSnapHelper
    lateinit var outfitCardList : List<CardModel>

    fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }

    val onSelectButtonPressed = View.OnClickListener { view: View ->
        val layoutManager = identityChooser.layoutManager!!
        val snapView = snapHelper.findSnapView(layoutManager)!!
        val snapPosition = layoutManager.getPosition(snapView)
        val selectedCard = outfitCardList[snapPosition]
        val title = selectedCard.title

        Navigation.findNavController(view).navigate(R.id.action_chooseIdentity_to_chooseCards)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.allCardsFromSelectedPacks.observe(this, Observer<List<CardModel>> { cardList->
            outfitCardList = cardList.filter { it.type == "Outfit"}.sortedByDescending { it.gang }
            identityChooser.adapter = FullScreenCardAdapter(outfitCardList)
            snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(identityChooser)
            selectButton.setOnClickListener(onSelectButtonPressed)
        })
    }
}
