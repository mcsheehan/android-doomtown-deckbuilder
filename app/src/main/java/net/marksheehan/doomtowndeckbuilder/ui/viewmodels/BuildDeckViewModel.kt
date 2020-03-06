package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.marksheehan.doomtowndeckbuilder.database.entitites.CardAndNumberOfCards
import net.marksheehan.doomtowndeckbuilder.database.entitites.PackEntity
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class BuildDeckViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    val allCardsFromSelectedPacks : LiveData<List<CardModel>> = cardRepository.getAllCardsFromSelectedPacks()

    fun allCardsAndNumberChosenForDeck(deckId : Long) :  LiveData<List<CardAndNumberOfCards>> {
        return cardRepository.getAllCardsForDeckId(deckId)
    }

}