package net.marksheehan.doomtowndeckbuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import net.marksheehan.doomtowndeckbuilder.database.PackEntity
import com.google.samples.apps.sunflower.data.RetrievePackDataRepository
import kotlinx.coroutines.launch
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


class CardViewerViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    val allCards : LiveData<List<CardModel>> = cardRepository.getAllSelectedDeckCards()
    val selectedPacks : LiveData<List<PackEntity>> = cardRepository.getAllPacks()

    fun updatePack(pack : PackEntity) {
        viewModelScope.launch {
            cardRepository.updatePack(pack)
        }
    }
}