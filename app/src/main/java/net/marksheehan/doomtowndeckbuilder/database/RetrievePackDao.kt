package net.marksheehan.doomtowndeckbuilder.database;


import androidx.lifecycle.LiveData
import androidx.room.*
import net.marksheehan.doomtowndeckbuilder.database.entitites.CardAndNumberOfCards
import net.marksheehan.doomtowndeckbuilder.database.entitites.CardIdAndNumberOf
import net.marksheehan.doomtowndeckbuilder.database.entitites.DeckEntity
import net.marksheehan.doomtowndeckbuilder.database.entitites.PackEntity
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel


@Dao
interface RetrievePackDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardList(cards : List<CardModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPack(pack : PackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPackList(packs : List<PackEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardIdAndNumberOfForDeck(cardIdAndNumberOf: CardIdAndNumberOf)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createDeck(deck : DeckEntity)

    @Query("SELECT CardModel.*, numberOfCards AS numberOfCards FROM CardIdAndNumberOf INNER JOIN CardModel ON CardIdAndNumberOf.cardId WHERE CardIdAndNumberOf.deckId IS :deckId")
    fun getAllCardAndNumberOfCardsForGivenDeckId(deckId : Long) : LiveData<List<CardAndNumberOfCards>>

    @Query("SELECT * FROM DeckEntity INNER JOIN CardModel ON DeckEntity.identityCardId == CardModel.cardId")
    fun getIdentityCardsAndDecks() : LiveData<List<CardAndDeck>>

    @Update
    fun updatePack(pack: PackEntity)

    @Query("SELECT * FROM CardModel")
    fun getAllCardsLiveList() : LiveData<List<CardModel>>

    @Query("SELECT * FROM CardModel")
    fun getAllCardsList() : List<CardModel>

    @Query("SELECT * FROM PackEntity")
    fun getAllPacksList() : List<PackEntity>

    @Query("SELECT * FROM DeckEntity")
    fun getAllDecksList() : LiveData<List<DeckEntity>>

    @Query("SELECT * FROM PackEntity")
    fun getAllPacksLiveList() : LiveData<List<PackEntity>>

    @Query("SELECT * FROM CardModel INNER JOIN PackEntity ON CardModel.pack == PackEntity.packname WHERE PackEntity.isSelected IS 1")
    fun getAllCardsFromSelectedPacks()  : LiveData<List<CardModel>>
}