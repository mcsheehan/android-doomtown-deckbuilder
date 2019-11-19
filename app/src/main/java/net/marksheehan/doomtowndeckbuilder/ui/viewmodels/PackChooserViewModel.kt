package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.marksheehan.doomtowndeckbuilder.database.PackEntity


class PackChooserViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    var livePacks : MutableLiveData<List<PackEntity>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val allPacks = cardRepository.getAllPacksList()
            livePacks.value = allPacks
        }
    }

    fun updatePack(pack : PackEntity) {
        pack.isSelected = !pack.isSelected

        val packCopy = livePacks.value
        packCopy?.forEach { it -> if(it.packname == pack.packname){it.isSelected = pack.isSelected} }

        livePacks.value = packCopy

        updatePackInDb(pack)
    }

    fun updatePackInDb(pack : PackEntity) {
        viewModelScope.launch {
            cardRepository.updatePack(pack)
        }
    }
}