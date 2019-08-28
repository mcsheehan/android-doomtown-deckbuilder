package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fullscreen_card.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class FullScreenCardFragment : Fragment(R.layout.fullscreen_card) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentCard : CardModel? = arguments?.getParcelable(null)

        val fullImagePath = "https://dtdb.co" + currentCard!!.imagesrc
        Picasso.get().load(fullImagePath).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(fullscreencard)
    }
}
