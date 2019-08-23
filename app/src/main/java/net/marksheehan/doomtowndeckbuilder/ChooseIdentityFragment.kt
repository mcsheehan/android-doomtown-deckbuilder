package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.choose_identity.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cardModel : List<CardModel> = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context!!,"card_list.json")

        outfitCardList = cardModel.filter { it.type == "Outfit" }

        identityChooser.adapter = FullScreenCardAdapter(outfitCardList)

        snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(identityChooser)

        selectButton.setOnClickListener(onSelectButtonPressed)


    }

}
