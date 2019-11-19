package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class CardAdapter(private val itemList: List<CardModel>, val onCardModelClicked : (CardModel)-> Unit) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView = itemView.findViewById(R.id.cardImage)

        fun bind(cardModel: CardModel){
            itemView.setOnClickListener{onCardModelClicked(cardModel)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_small_view, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = itemList[position]
        holder.bind(currentCard)
        Picasso.get().load(currentCard.getImagePath()).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(holder.cardImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
