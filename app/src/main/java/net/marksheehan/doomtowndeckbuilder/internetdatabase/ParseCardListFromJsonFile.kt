package net.marksheehan.doomtowndeckbuilder.internetdatabase

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.marksheehan.doomtowndeckbuilder.datamodel.MoshiCardModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


class ParseCardListFromJsonFile{

    companion object {

        inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

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

        fun parseCardsFromMoshi(context: Context): List<MoshiCardModel> {
            val moshi: Moshi = Moshi.Builder()
                    // ... add your own JsonAdapters and factories ...
                    .add(KotlinJsonAdapterFactory())
                    .build()

            val listType = Types.newParameterizedType(List::class.java, MoshiCardModel::class.java)
            val card_adapter = moshi.adapter(MoshiCardModel::class.java)
            val card_list_adapter: JsonAdapter<List<MoshiCardModel>> = moshi.adapter(listType)

            val json = readAssetFileToString(context, "card_list.json")
            val cards = card_list_adapter.fromJson(json)!!

            return cards
        }
    }
}