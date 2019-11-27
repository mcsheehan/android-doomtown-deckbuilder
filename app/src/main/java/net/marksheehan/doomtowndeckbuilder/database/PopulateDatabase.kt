package net.marksheehan.doomtowndeckbuilder.database

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope
import net.marksheehan.doomtowndeckbuilder.database.entitites.PackEntity
import net.marksheehan.doomtowndeckbuilder.internetdatabase.ParseCardListFromJsonFileUsingGson
import net.marksheehan.doomtowndeckbuilder.ui.viewmodels.CardSerialisationUtilities

class PopulateDatabase(context : Context, workerParams : WorkerParameters) : CoroutineWorker(context, workerParams){

    private val TAG by lazy { PopulateDatabase::class.java.simpleName }

    companion object{
        fun populateDatabase(context : Context) {

            val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(context,"card_list.json")
            val packList = CardSerialisationUtilities.createCardPackList(cardModel)

            val packEntityList : MutableList<PackEntity> = mutableListOf()

            for(pack in packList ){
                packEntityList.add(PackEntity(pack, true))
            }

            MainDatabase.getInstance(context).cardEntityDao().insertPackList(packEntityList)
            MainDatabase.getInstance(context).cardEntityDao().insertCardList(cardModel)
        }
    }

    override suspend fun doWork(): Result = coroutineScope{
//            populateDatabase(applicationContext)
            val cardModel = ParseCardListFromJsonFileUsingGson.parseCardListFromAssetFile(applicationContext,"card_list.json")
            val packList = CardSerialisationUtilities.createCardPackList(cardModel)

            val packEntityList : MutableList<PackEntity> = mutableListOf()

            for(pack in packList ){
                packEntityList.add(PackEntity(pack, true))
            }

            MainDatabase.getInstance(applicationContext).cardEntityDao().insertPackList(packEntityList)
            MainDatabase.getInstance(applicationContext).cardEntityDao().insertCardList(cardModel)
            Result.success()
    }

}