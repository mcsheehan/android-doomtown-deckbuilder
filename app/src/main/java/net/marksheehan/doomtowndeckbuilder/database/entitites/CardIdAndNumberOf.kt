package net.marksheehan.doomtowndeckbuilder.database.entitites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

data class CardAndNumberOfCards(@Embedded val card : CardModel, val numberOfCards: Long)

@Entity(primaryKeys = ["cardId","deckId"],
        foreignKeys =
        [ForeignKey(entity = CardModel::class, parentColumns = arrayOf("cardId"), childColumns = arrayOf("cardId")),
         ForeignKey(entity = DeckEntity::class, parentColumns = arrayOf("deckId"), childColumns = arrayOf("deckId"))])
data class CardIdAndNumberOf (
    val deckId: Long,
    val cardId: Long,

    val numberOfCards: Int = 0
)