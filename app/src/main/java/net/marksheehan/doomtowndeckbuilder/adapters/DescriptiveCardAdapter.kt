package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
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

    inner class CardViewHolder(private val binding: DescriptiveCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindCardModelToViewHolder(cardModel: CardModelAndNumberSelected) {

            binding.descriptiveCardModel = cardModel

            val suitSymbol : String = when(cardModel.cardModel.suit){
                "Diams" -> "♦"
                "Hearts" -> "♥"
                "Spades" -> "♠"
                "Clubs" -> "♣"
                else -> ""
            }

            val numberString : String = when(cardModel.cardModel.rank){
                1L -> "A"
                in 2L..10L -> cardModel.cardModel.rank.toString()
                11L -> "J"
                12L -> "Q"
                13L -> "K"
                else -> ""
            }

            val suitAndRank : String = numberString + suitSymbol
            binding.suit.text = suitAndRank

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
