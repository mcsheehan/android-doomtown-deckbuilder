package net.marksheehan.doomtowndeckbuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


class CardViewerViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    val allCardsFromSelectedPacks : LiveData<List<CardModel>> = cardRepository.getAllCardsFromSelectedPacks()

}