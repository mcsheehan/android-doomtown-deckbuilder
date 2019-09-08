package net.marksheehan.doomtowndeckbuilder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName= "DeckEntity")
class DeckEntity(
    @PrimaryKey
    val deckname : String
)