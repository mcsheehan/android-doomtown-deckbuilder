package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.choose_identity.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.internetdatabase.DoomtownDbAccess
import androidx.recyclerview.widget.LinearSnapHelper

class ChooseIdentityFragment : Fragment()
{
    internal lateinit var cardAdapter: CardAdapter

    internal var cardResult: DoomtownDbAccess.CardQueryCallback = object : DoomtownDbAccess.CardQueryCallback {

        override fun success(cardListResult: List<CardModel>?) {

            val clubs = cardListResult?.filter { it.suit == "Clubs" }
            val outfitCardList = cardListResult?.filter { it.type == "Outfit" }

            cardAdapter = CardAdapter(outfitCardList!!)

            identityChooser.adapter = cardAdapter
            cardAdapter.notifyDataSetChanged()
        }

        override fun failure() {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_identity, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(identityChooser)
        val doomtownDbAccess = DoomtownDbAccess()
        doomtownDbAccess.sendServerRequestCardList(cardResult)
    }
}