package net.marksheehan.doomtowndeckbuilder.internetdatabase

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.marksheehan.doomtowndeckbuilder.datamodel.MoshiCardModel

fun parseCardsFromMoshi(context: Context): List<MoshiCardModel> {
    val moshi: Moshi = Moshi.Builder()
            // ... add your own JsonAdapters and factories ...
            .add(KotlinJsonAdapterFactory())
            .build()

    val listType = Types.newParameterizedType(List::class.java, MoshiCardModel::class.java)
    val card_adapter = moshi.adapter(MoshiCardModel::class.java)
    val card_list_adapter: JsonAdapter<List<MoshiCardModel>> = moshi.adapter(listType)

    val json = ParseCardListFromJsonFile.readAssetFileToString(context, "card_list.json")
    val cards = card_list_adapter.fromJson(json)!!

    return cards
}