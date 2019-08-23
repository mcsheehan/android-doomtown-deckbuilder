package net.marksheehan.doomtowndeckbuilder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fullscreen_card.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class FullScreenCardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fullscreen_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentCard : CardModel? = arguments?.getParcelable(null)

        val fullImagePath = "https://dtdb.co" + currentCard!!.imagesrc
        Picasso.get().load(fullImagePath).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(fullscreencard)
    }
}
