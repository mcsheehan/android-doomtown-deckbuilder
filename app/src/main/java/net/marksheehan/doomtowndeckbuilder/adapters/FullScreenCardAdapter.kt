package net.marksheehan.doomtowndeckbuilder.adapters

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fullscreen_card.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class FullScreenCardAdapter(private val mItemList: List<CardModel>) : RecyclerView.Adapter<FullScreenCardAdapter.FullScreenardViewHolder>() {

    inner class FullScreenardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun getClickListenerForCardPosition(position: Int) : OnClickListener{

        val clickListener = OnClickListener { view: View  ->
            val currentCard = mItemList.get(position)
            val bundle = Bundle()
            bundle.putParcelable(null, currentCard)
            Navigation.findNavController(view).navigate(R.id.action_cardViewerFragment_to_individualCardViewer, bundle)
        }

        return clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullScreenardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fullscreen_card, parent, false)
        return FullScreenardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FullScreenardViewHolder, position: Int) {
        val currentCard = mItemList[position]
        val fullImagePath = "https://dtdb.co" + currentCard.imagesrc!!

        Picasso.get().load(fullImagePath).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(holder.itemView.fullscreencard)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }
}
