package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
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

    fun createNewDeck(cardModel: CardModel, deckName : String, deckDescription : String){

        val newDeck = DeckEntity(identityCardId = cardModel.cardId, deckname = deckName, description = deckDescription)
        val createDeckJob = viewModelScope.launch {
            cardRepository.createDeck(newDeck)
        }
    }

    init {
        outfitCardsSorted.addSource(cardsFromSelectedPacks) { cardModelList: List<CardModel> ->
            outfitCardsSorted.value = cardModelList.filter { it.type == "Outfit" }.sortedByDescending { it.gang }
        }
    }
}
