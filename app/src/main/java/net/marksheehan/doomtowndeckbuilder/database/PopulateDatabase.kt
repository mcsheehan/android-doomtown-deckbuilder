package net.marksheehan.doomtowndeckbuilder.database

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoThingsToCardLists

class PopulateDatabase(context : Context, workerParams : WorkerParameters) : CoroutineWorker(context, workerParams){

    private val TAG by lazy { PopulateDatabase::class.java.simpleName }

    companion object{
        fun populateDatabase(context : Context) {

            val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context,"card_list.json")
            val packList = DoThingsToCardLists.createCardPackList(cardModel)

            val packEntityList : MutableList<PackEntity> = mutableListOf()

            for(pack in packList ){
                packEntityList.add(PackEntity(pack, true))
            }

            MainDatabase.getInstance(context).cardEntityDao().updateSelectedPack(packEntityList)
            MainDatabase.getInstance(context).cardEntityDao().addCardsToDataBase(cardModel)
        }
    }

    override suspend fun doWork(): Result = coroutineScope{
//            populateDatabase(applicationContext)
            val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(applicationContext,"card_list.json")
            val packList = DoThingsToCardLists.createCardPackList(cardModel)

            val packEntityList : MutableList<PackEntity> = mutableListOf()

            for(pack in packList ){
                packEntityList.add(PackEntity(pack, true))
            }

            MainDatabase.getInstance(applicationContext).cardEntityDao().updateSelectedPack(packEntityList)
            MainDatabase.getInstance(applicationContext).cardEntityDao().addCardsToDataBase(cardModel)
            Result.success()
    }

}