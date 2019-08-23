package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.card_list.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson

class CardViewerFragment : Fragment() {

    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_viewer_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cardModel : List<CardModel> = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context!!,"card_list.json")

        cardAdapter = CardAdapter(cardModel)
        card_recycler.adapter = cardAdapter
        cardAdapter.notifyDataSetChanged()
    }
}