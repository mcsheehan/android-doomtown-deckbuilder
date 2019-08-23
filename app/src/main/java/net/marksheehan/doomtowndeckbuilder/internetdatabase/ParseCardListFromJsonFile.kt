package net.marksheehan.doomtowndeckbuilder.internetdatabase

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class ParseCardListFromJsonFile{

    companion object {

        fun readAssetFileToString(context : Context, fileName : String) : String {
            val inputStream = context.assets.open(fileName)
            val stringContainingFile : String = inputStream.bufferedReader().use { it.readText() }
            return stringContainingFile
        }

        fun parseJsonToCardList(json : String) : List<CardModel>{
            val cardModelType = object : TypeToken<List<CardModel>>() {}.type
            val cardListResult = Gson().fromJson<List<CardModel>>(json, cardModelType)
            return cardListResult
        }

        fun parseCardListFromAssetFile(context: Context, fileName : String) : List<CardModel>{
            val json = readAssetFileToString(context, fileName)
            return parseJsonToCardList(json)
        }
    }
}