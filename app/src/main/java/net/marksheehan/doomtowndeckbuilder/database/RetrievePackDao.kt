package net.marksheehan.doomtowndeckbuilder.database;


import androidx.lifecycle.LiveData
import androidx.room.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


@Dao
interface RetrievePackDao{

    @Query("SELECT * FROM CardModel")
    fun getAllCards() : LiveData<List<CardModel>>

    @Query("SELECT * FROM CardModel")
    fun getAllCardsNotLive() : List<CardModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCardsToDataBase(cards : List<CardModel>)


    @Query("SELECT * FROM PackEntity")
    fun getAllPacksNotLive() : List<PackEntity>

    @Query("SELECT * FROM PackEntity")
    fun getAllSelectedPacks() : LiveData<List<PackEntity>>

    @Update
    fun updatePack(pack: PackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateSelectedPack(pack : PackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateSelectedPack(packs : List<PackEntity>)


    @Query("SELECT * FROM CardModel INNER JOIN PackEntity ON CardModel.pack == PackEntity.packname WHERE PackEntity.isSelected IS 1")
    fun getAllCardsFromSelectedPacks()  : LiveData<List<CardModel>>
}