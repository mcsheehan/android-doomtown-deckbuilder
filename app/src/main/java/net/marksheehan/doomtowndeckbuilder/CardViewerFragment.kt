package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController

import kotlinx.android.synthetic.main.card_list.*

class CardViewerFragment : Fragment() {

    internal lateinit var cardAdapter: CardAdapter

    internal var cardResult: DoomtownDbAccess.CardQueryCallback = object : DoomtownDbAccess.CardQueryCallback {

        override fun success(result: List<CardModel>?) {

            cardAdapter = CardAdapter(result!!)

            card_recycler.adapter = cardAdapter
            cardAdapter.notifyDataSetChanged()
        }

        override fun failure() {
            Snackbar.make(this@CardViewerFragment.view!!.findViewById(R.id.card_recycler), R.string.internet_connection_failure, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_viewer_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val doomtownDbAccess = DoomtownDbAccess()
        doomtownDbAccess.sendServerRequestCardList(cardResult)
    }
}