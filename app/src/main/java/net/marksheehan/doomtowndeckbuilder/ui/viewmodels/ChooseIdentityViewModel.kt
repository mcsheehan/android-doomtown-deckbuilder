package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import android.provider.Contacts
import androidx.lifecycle.*
import kotlinx.coroutines.*
import net.marksheehan.doomtowndeckbuilder.database.entitites.DeckEntity
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class ChooseIdentityViewModel internal constructor(private val cardRepository: RetrievePackDataRepository) : ViewModel() {

    private val cardsFromSelectedPacks: LiveData<List<CardModel>> = cardRepository.getAllCardsFromSelectedPacks()

    private val outfitCardsSorted = MediatorLiveData<List<CardModel>>()

    val filteredData: LiveData<List<CardModel>> get() = Transformations.map(cardsFromSelectedPacks) { cardModelList: List<CardModel> ->
        cardModelList.filter { it.type == "Outfit" }
                .filter {
                    when (currentFilter) {
                        ValidOutfits.NoFilter -> {true}
                        else -> {it.gang == currentFilter.filter }
                    }
                }
                .sortedByDescending { it.gang }
    }

    enum class ValidOutfits(val filter: String) {
        NoFilter(""),
        Outlaws("Outlaws"),
        Entrepreneurs("Entrepreneurs")
    }

    var currentFilter: ValidOutfits = ValidOutfits.NoFilter

    fun changeCurrentFilter(filter: ValidOutfits) {
        currentFilter = filter
    }

    fun createDeckEntity(cardModel: CardModel, deckName : String, deckDescription : String) : DeckEntity{
        val newDeck = DeckEntity(identityCardId = cardModel.cardId, deckname = deckName, description = deckDescription)
        return newDeck
    }

    fun createNewDeck(newDeck : DeckEntity) : Deferred<Unit>{
        return viewModelScope.async { cardRepository.createDeck(newDeck) }
    }

    init {
        outfitCardsSorted.addSource(cardsFromSelectedPacks) { cardModelList: List<CardModel> ->
            outfitCardsSorted.value = cardModelList.filter { it.type == "Outfit" }.sortedByDescending { it.gang }
        }
    }
}
