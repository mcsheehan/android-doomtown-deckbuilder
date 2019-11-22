package net.marksheehan.doomtowndeckbuilder.ui.viewmodels

import android.app.Activity
import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson

class CardSerialisationUtilities(application: Application) {
    val cards : List<CardModel> = loadInitialCardList(application)

    private fun loadInitialCardList(context : Context)  : List<CardModel> {
        val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context,"card_list.json")
        return cardModel
    }

    companion object {
        fun createCardPackList(cards: List<CardModel>): Set<String> {
            val allPacksSet: MutableSet<String> = mutableSetOf()
            cards.forEach { card: CardModel -> allPacksSet.add(card.pack) }
            return allPacksSet
        }

        private fun deserialiseSelectedPackMapFromSharedPreferences(activity: Activity): Map<String, Boolean>? {

            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

            val defaultValue = ""
            val jsonString = sharedPref.getString("selectedPacks", defaultValue)
            if (jsonString == defaultValue) {
                return null
            }

            val cardModelType = object : TypeToken<Map<String, Boolean>>() {}.type
            val cardListResult = Gson().fromJson<Map<String, Boolean>>(jsonString, cardModelType)
            return cardListResult
        }
    }
}