package net.marksheehan.doomtowndeckbuilder

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.mark.doomtowndeckbuilder.R
import com.squareup.picasso.Picasso

class CardAdapter(private val mItemList: List<CardModel>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cost: TextView
        var cardName: TextView
        var cardImage: ImageView

        init {
            cardName = itemView.findViewById(R.id.cardName)
            cost = itemView.findViewById(R.id.cost)
            cardImage = itemView.findViewById(R.id.cardImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = mItemList[position]

        val cost = "" + currentCard.cost

        holder.cardName.text = currentCard.title
        holder.cost.text = cost

        val fullImagePath = "https://dtdb.co" + currentCard.imagesrc!!
        Picasso.get().load(fullImagePath).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.cardImage)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }
}
