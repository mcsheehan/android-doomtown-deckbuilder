package net.marksheehan.doomtowndeckbuilder.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson

class DoomtownCardsViewModel(context : Context) : ViewModel(){

    class DoomtownCardsViewModelFactory(val context : Context) : ViewModelProvider.Factory {
        override fun <DoomtownCardsViewModel : ViewModel> create(modelClass: Class<DoomtownCardsViewModel>) : DoomtownCardsViewModel {
            return DoomtownCardsViewModel(context) as DoomtownCardsViewModel
        }
    }

    val cards : List<CardModel> = loadInitialCardList(context)

    private fun loadInitialCardList(context : Context)  : List<CardModel> {
        val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context,"card_list.json")
        return cardModel
    }
}