package net.marksheehan.doomtowndeckbuilder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fullscreen_card.*

class FullScreenCardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fullscreen_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentCard : CardModel? = arguments?.getParcelable(null)

        val fullImagePath = "https://dtdb.co" + currentCard!!.imagesrc
        Picasso.get().load(fullImagePath).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(fullscreencard)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

}
