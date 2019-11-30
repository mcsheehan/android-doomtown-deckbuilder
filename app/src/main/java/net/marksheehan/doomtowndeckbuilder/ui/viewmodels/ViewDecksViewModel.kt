package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.marksheehan.doomtowndeckbuilder.database.CardAndDeck

class ViewDecksViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    val cardsAndDecks  : LiveData<List<CardAndDeck>> = cardRepository.getIdentityCardsAndDecks()
}