package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_fullscreen_view.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.databinding.DescriptiveCardViewBinding
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModelAndNumberSelected

class DescriptiveCardAdapter(val onCardModelClicked : (CardModelAndNumberSelected)-> Unit) : ListAdapter<CardModel, DescriptiveCardAdapter.CardViewHolder>(CardModelDiff())  {

    class CardModelDiff : DiffUtil.ItemCallback<CardModel>() {

        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return (oldItem.cardId == newItem.cardId)
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return (oldItem.cardId != newItem.cardId)
        }
    }

    inner class CardViewHolder(public val binding: DescriptiveCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindCardModelToViewHolder(cardModel: CardModelAndNumberSelected) {

            binding.descriptiveCardModel = cardModel
            Picasso.get().load(cardModel.cardModel.getImagePath()).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(binding.cardImage)
            itemView.setOnClickListener{onCardModelClicked(cardModel)}

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding : DescriptiveCardViewBinding = DescriptiveCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(cardViewHolder: CardViewHolder, position: Int) {
        val currentCard = getItem(position)
        val modelAndNumberSelected = CardModelAndNumberSelected(currentCard, 0)
        cardViewHolder.bindCardModelToViewHolder(modelAndNumberSelected)
    }
}
