package net.marksheehan.doomtowndeckbuilder.ui.viewmodels


import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import net.marksheehan.doomtowndeckbuilder.database.entitites.PackEntity
import net.marksheehan.doomtowndeckbuilder.database.RetrievePackDao
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class RetrievePackDataRepository private constructor(
        private val cardPackDao: RetrievePackDao
)
{
    suspend fun createCards(card : List<CardModel>) {
        withContext(IO) {
            cardPackDao.insertCardList(card)
        }
    }

    fun getAllCardsFromSelectedPacks() = cardPackDao.getAllCardsFromSelectedPacks()

    suspend fun getAllPacksList() : List<PackEntity>{
        return withContext(IO) {
            cardPackDao.getAllPacksList()
        }
    }

    suspend fun updatePack(pack : PackEntity) {
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