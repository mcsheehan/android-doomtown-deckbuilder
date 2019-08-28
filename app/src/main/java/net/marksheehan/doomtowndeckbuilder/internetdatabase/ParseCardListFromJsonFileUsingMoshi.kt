package net.marksheehan.doomtowndeckbuilder.internetdatabase

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.marksheehan.doomtowndeckbuilder.datamodel.MoshiCardModel

class ParseCardListFromJsonFileUsingMoshi
{
    companion object
    {
        fun parseCardsFromAssetFile(context: Context, fileName : String): List<MoshiCardModel> {
            val moshi: Moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            val listType = Types.newParameterizedType(List::class.java, MoshiCardModel::class.java)
            val card_list_adapter: JsonAdapter<List<MoshiCardModel>> = moshi.adapter(listType)

            val json = ParseCardListFromJsonFileUsingGson.readAssetFileToString(context, fileName)
            val cards = card_list_adapter.fromJson(json)!!

            return cards
        }
    }
}