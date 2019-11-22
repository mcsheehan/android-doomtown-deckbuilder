package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


class CardViewerViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    private val cardsFromSelectedPacks: LiveData<List<CardModel>> = cardRepository.getAllCardsFromSelectedPacks()
    val cardsFromSelectedPacksSorted = MediatorLiveData<List<CardModel>>()

    init {
        cardsFromSelectedPacksSorted.addSource(cardsFromSelectedPacks) { cardModelList: List<CardModel> ->
            cardsFromSelectedPacksSorted.value = cardModelList.sortedByDescending { it.suit }
        }
    }
}