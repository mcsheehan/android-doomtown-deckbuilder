package net.marksheehan.doomtowndeckbuilder.viewmodels


import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import net.marksheehan.doomtowndeckbuilder.database.PackEntity
import net.marksheehan.doomtowndeckbuilder.database.RetrievePackDao
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class RetrievePackDataRepository private constructor(
        private val cardPackDao: RetrievePackDao
)
{
    suspend fun createCards(card : List<CardModel>) {
        withContext(IO) {
            cardPackDao.addCardsToDataBase(card)
        }
    }

    fun getAllCardsFromSelectedPacks() = cardPackDao.getAllCardsFromSelectedPacks()
    fun getAllCards() = cardPackDao.getAllCards()
    fun getAllCardsNotLive() = cardPackDao.getAllCardsNotLive()


    fun getAllPacks() = cardPackDao.getAllSelectedPacks()

    suspend fun getAllPacksNotLive() : List<PackEntity>{
        return withContext(IO) {
            cardPackDao.getAllPacksNotLive()
        }
    }

    suspend fun updatePack(pack : PackEntity) {
        withContext(IO) {
            cardPackDao.updatePack(pack)
        }
    }

    suspend fun getPacks(pack : PackEntity) {
        withContext(IO) {
            cardPackDao.updatePack(pack)
        }
    }


    companion object {
        // For Singleton instantiation
        @Volatile private var instance: RetrievePackDataRepository? = null

        fun getInstance(cardPackDao: RetrievePackDao) =
                instance ?: synchronized(this) {
                    instance ?: RetrievePackDataRepository(cardPackDao).also { instance = it }
                }
    }
}