package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment

import com.example.mark.doomtowndeckbuilder.R

import kotlinx.android.synthetic.main.card_viewer_fragment.*
import kotlinx.android.synthetic.main.card_list.*

class CardViewerFragment : Fragment() {

    internal lateinit var cardAdapter: CardAdapter

    val onItemClicked = View.OnClickListener { view: View ->
        Snackbar.make(view, R.string.downloading_card_data, Snackbar.LENGTH_LONG).show()
    }

    internal var cardResult: DoomtownDbAccess.CardQueryCallback = object : DoomtownDbAccess.CardQueryCallback {

        override fun success(result: List<CardModel>?) {
            cardAdapter = CardAdapter(result!!)

            card_recycler.adapter = cardAdapter
            cardAdapter.notifyDataSetChanged()

            card_recycler.setOnClickListener(onItemClicked)
        }

        override fun failure() {
            Snackbar.make(this@CardViewerFragment.view!!.findViewById(R.id.fab), R.string.internet_connection_failure, Snackbar.LENGTH_LONG);
//            Snackbar.make(this@CardViewerFragment.findViewById<View>(R.id.fab), R.string.internet_connection_failure, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.card_viewer_fragment, container, false);
    }

    val fabClick = View.OnClickListener { view: View ->
        Snackbar.make(view, R.string.downloading_card_data, Snackbar.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener(fabClick)

        val doomtownDbAccess = DoomtownDbAccess()
        doomtownDbAccess.sendServerRequestCardList(cardResult)
    }
}