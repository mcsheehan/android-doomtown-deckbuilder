package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_small_view.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class CardAdapter(val onCardModelClicked : (CardModel)-> Unit) : ListAdapter<CardModel, CardAdapter.CardViewHolder>(CardModelDiff())  {

    class CardModelDiff : DiffUtil.ItemCallback<CardModel>() {

        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return (oldItem.cardId == newItem.cardId)
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return (oldItem.cardId != newItem.cardId)
        }
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCardModelToViewHolder(cardModel: CardModel){
            itemView.setOnClickListener{onCardModelClicked(cardModel)}

            Picasso.get()
                    .load(cardModel.getImagePath())
                    .placeholder(R.drawable.card_back)
                    .error(R.drawable.card_back)
                    .into(itemView.cardImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_small_view, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(cardViewHolder: CardViewHolder, position: Int) {
        val currentCard = getItem(position)
        cardViewHolder.bindCardModelToViewHolder(currentCard)
    }
}
