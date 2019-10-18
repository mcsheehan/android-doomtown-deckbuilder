package net.marksheehan.doomtowndeckbuilder.database;


import androidx.lifecycle.LiveData
import androidx.room.*
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


@Dao
interface RetrievePackDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardList(cards : List<CardModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPack(pack : PackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPackList(packs : List<PackEntity>)

    @Update
    fun updatePack(pack: PackEntity)

    @Query("SELECT * FROM CardModel")
    fun getAllCardsLiveList() : LiveData<List<CardModel>>

    @Query("SELECT * FROM CardModel")
    fun getAllCardsList() : List<CardModel>

    @Query("SELECT * FROM PackEntity")
    fun getAllPacksList() : List<PackEntity>

    @Query("SELECT * FROM PackEntity")
    fun getAllPacksLiveList() : LiveData<List<PackEntity>>

    @Query("SELECT * FROM CardModel INNER JOIN PackEntity ON CardModel.pack == PackEntity.packname WHERE PackEntity.isSelected IS 1")
    fun getAllCardsFromSelectedPacks()  : LiveData<List<CardModel>>
}