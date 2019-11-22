package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_fullscreen_view.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class FullScreenCardAdapter(private val mItemList: List<CardModel>) : RecyclerView.Adapter<FullScreenCardAdapter.FullScreenCardViewHolder>() {

    inner class FullScreenCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullScreenCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_fullscreen_view, parent, false)
        return FullScreenCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FullScreenCardViewHolder, position: Int) {
        val currentCard = mItemList[position]
        val fullImagePath = "https://dtdb.co" + currentCard.imagesrc!!

        Picasso.get().load(fullImagePath).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(holder.itemView.fullscreencard)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }
}
