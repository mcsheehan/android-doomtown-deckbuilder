package net.marksheehan.doomtowndeckbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class BuildDeckViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {
    val allCardsFromSelectedPacks : LiveData<List<CardModel>> = cardRepository.getAllCardsFromSelectedPacks()
}