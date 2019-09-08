/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.data

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

    suspend fun updatePack(pack : PackEntity) {
        withContext(IO) {
            cardPackDao.updatePack(pack)
        }
    }
//
//    suspend fun removeGardenPlanting(gardenPlanting: CardModel) {
//        withContext(IO) {
//            cardPackDao.deleteGardenPlanting(gardenPlanting)
//        }
//    }

//    fun getGardenPlantingForPlant(plantId: String) =
//            cardPackDao.getGardenPlantingForPlant(plantId)

    fun getAllCards() = cardPackDao.getAllCards()

    fun getAllSelectedDeckCards() = cardPackDao.getAllCardsFromSelectedDecks()

    fun getAllCardsNotLive() = cardPackDao.getAllCardsNotLive()

    fun getAllPacks() = cardPackDao.getAllSelectedPacks()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: RetrievePackDataRepository? = null

        fun getInstance(cardPackDao: RetrievePackDao) =
                instance ?: synchronized(this) {
                    instance ?: RetrievePackDataRepository(cardPackDao).also { instance = it }
                }
    }
}