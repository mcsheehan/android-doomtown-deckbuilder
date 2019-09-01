package net.marksheehan.doomtowndeckbuilder.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson

class DoomtownCardsViewModel(context : Context) : ViewModel(){

    class DoomtownCardsViewModelFactory(val context : Context) : ViewModelProvider.Factory {
        override fun <DoomtownCardsViewModel : ViewModel> create(modelClass: Class<DoomtownCardsViewModel>) : DoomtownCardsViewModel {
            return DoomtownCardsViewModel(context) as DoomtownCardsViewModel
        }
    }

    val cards : List<CardModel> = loadInitialCardList(context)
    val cardPacks : Set<String> = createCardPackList()
    val selectedCardPacks : MutableLiveData<MutableMap<String, Boolean>> = loadInitialSelectedCardPacks()

    private fun loadInitialCardList(context : Context)  : List<CardModel> {
        val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context,"card_list.json")
        return cardModel
    }

    private fun createCardPackList() : Set<String> {
        val allPacksSet : MutableSet<String> = mutableSetOf()
        cards.forEach { card: CardModel ->  allPacksSet.add(card.pack) }

        return allPacksSet
    }

    private fun deserialiseSelectedPackMapFromSharedPreferences(activity : Activity): Map<String, Boolean>? {

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        val defaultValue = ""
        val jsonString = sharedPref.getString("selectedPacks", defaultValue)
        if(jsonString == defaultValue)
        {
            return null
        }

        val cardModelType = object : TypeToken<Map<String, Boolean>>() {}.type
        val cardListResult = Gson().fromJson<Map<String,Boolean>>(jsonString, cardModelType)
        return cardListResult
    }

    private fun loadInitialSelectedCardPacks(context: Context) : MutableLiveData<MutableMap<String, Boolean>> {
        //Load first. If this fails then initialise
        deserialiseSelectedPackMapFromSharedPreferences()

        val cardPacksChecked =  MutableLiveData<MutableMap<String, Boolean>>()
        val cardPacksSelected : MutableMap<String, Boolean> = mutableMapOf()

        cardPacks.forEach { cardPacksSelected.set(it, true)}

        cardPacksChecked.value = cardPacksSelected
        return cardPacksChecked
    }

    fun filterCardsBySelectedPacks(): List<CardModel> {
        return cards.filter { selectedCardPacks.value!![it.pack]!! }
    }
}