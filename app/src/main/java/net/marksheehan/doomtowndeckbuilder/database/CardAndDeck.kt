package net.marksheehan.doomtowndeckbuilder.database

import androidx.room.Embedded
import net.marksheehan.doomtowndeckbuilder.database.entitites.DeckEntity
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

data class CardAndDeck(@Embedded val card : CardModel, @Embedded val deckEntity: DeckEntity)