package net.marksheehan.doomtowndeckbuilder.database.entitites

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

@Entity(tableName="DeckEntity")
class DeckEntity(
    @PrimaryKey
    val deckId : Long,

    @ForeignKey(entity = CardModel::class, parentColumns = arrayOf("identityCardId"), childColumns = arrayOf("cardId"))
    val identityCardId : Long,

    val deckname : String,
    val description : String
)