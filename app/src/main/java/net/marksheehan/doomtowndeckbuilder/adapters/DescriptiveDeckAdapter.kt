package net.marksheehan.doomtowndeckbuilder.adapters

import net.marksheehan.doomtowndeckbuilder.database.entitites.DeckEntity
import net.marksheehan.doomtowndeckbuilder.databinding.DescriptiveDeckViewBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModelAndNumberSelected

class DescriptiveDeckAdapter(val onCardModelClicked : (DeckEntity )-> Unit) : ListAdapter<DeckEntity, DescriptiveDeckAdapter.DeckViewHolder>(DeckModelDiff())  {

    class DeckModelDiff : DiffUtil.ItemCallback<DeckEntity>() {

        override fun areItemsTheSame(oldItem: DeckEntity, newItem: DeckEntity): Boolean {
            return (oldItem.deckId == newItem.deckId)
        }

        override fun areContentsTheSame(oldItem: DeckEntity, newItem: DeckEntity): Boolean {
            return (oldItem.deckId != newItem.deckId)
        }
    }

    inner class DeckViewHolder(private val binding: DescriptiveDeckViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCardModelToViewHolder(deckEntity: DeckEntity) {
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding : DescriptiveDeckViewBinding = DescriptiveDeckViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(deckViewHolder: DeckViewHolder, position: Int) {
        val currentDeck = getItem(position)
        deckViewHolder.bindCardModelToViewHolder(currentDeck)
    }
}
