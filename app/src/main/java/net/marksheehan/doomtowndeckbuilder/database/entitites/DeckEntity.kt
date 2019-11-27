package net.marksheehan.doomtowndeckbuilder.database.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "DeckEntity")
class DeckEntity(
    @PrimaryKey
    val deckname : String,
    val description : String
)