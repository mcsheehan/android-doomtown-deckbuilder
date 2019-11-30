package net.marksheehan.doomtowndeckbuilder.adapters

import net.marksheehan.doomtowndeckbuilder.database.entitites.DeckEntity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_fullscreen_view.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.database.CardAndDeck
import net.marksheehan.doomtowndeckbuilder.databinding.DescriptiveDeckItemBinding

class DescriptiveDeckAdapter(val onCardModelClicked : (CardAndDeck )-> Unit) : ListAdapter<CardAndDeck, DescriptiveDeckAdapter.DeckViewHolder>(DeckModelDiff())  {

    class DeckModelDiff : DiffUtil.ItemCallback<CardAndDeck>() {

        override fun areItemsTheSame(oldItem: CardAndDeck, newItem: CardAndDeck): Boolean {
            return (oldItem.deckEntity.deckId == newItem.deckEntity.deckId)
        }

        override fun areContentsTheSame(oldItem: CardAndDeck, newItem: CardAndDeck): Boolean {
            return (oldItem.deckEntity.deckId != newItem.deckEntity.deckId)
        }
    }

    inner class DeckViewHolder(private val binding: DescriptiveDeckItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindDeckEntityToDeckViewHolder(cardAndDeck: CardAndDeck) {
            binding.cardAndDeck = cardAndDeck
            Picasso.get().load(cardAndDeck.card.getImagePath()).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(binding.identityCardImage)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding : DescriptiveDeckItemBinding = DescriptiveDeckItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(deckViewHolder: DeckViewHolder, position: Int) {
        val currentDeck = getItem(position)
        deckViewHolder.bindDeckEntityToDeckViewHolder(currentDeck)
    }
}
