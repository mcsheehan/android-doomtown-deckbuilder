package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.choose_identity.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import net.marksheehan.doomtowndeckbuilder.adapters.FullScreenCardAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class ChooseIdentityFragment : Fragment()
{
    lateinit var snapHelper : LinearSnapHelper
    lateinit var outfitCardList : List<CardModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_identity, container, false);
    }

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
        val cards= ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java].cards

        outfitCardList = cards.filter { it.type == "Outfit" }.sortedByDescending { it.gang }

        identityChooser.adapter = FullScreenCardAdapter(outfitCardList)

        snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(identityChooser)

        selectButton.setOnClickListener(onSelectButtonPressed)
    }

}
