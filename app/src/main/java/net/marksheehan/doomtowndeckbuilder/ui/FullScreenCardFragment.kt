package net.marksheehan.doomtowndeckbuilder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_fullscreen_view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class FullScreenCardFragment : Fragment(R.layout.card_fullscreen_view) {

    private val args: FullScreenCardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentCard = args.card
        Picasso.get().load(currentCard.getImagePath()).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(fullscreencard)
    }
}
