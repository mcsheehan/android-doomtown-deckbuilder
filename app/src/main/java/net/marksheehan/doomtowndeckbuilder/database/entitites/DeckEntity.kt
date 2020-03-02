package net.marksheehan.doomtowndeckbuilder.database.entitites

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

@Entity(tableName="DeckEntity")
class DeckEntity(
    @PrimaryKey(autoGenerate = true)
    val deckId : Long = 0,

    @ForeignKey(entity = CardModel::class, parentColumns = arrayOf("identityCardId"), childColumns = arrayOf("cardId"))
    val identityCardId : Long,

    val deckname : String,
    val description : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString()!!,
            parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(deckId)
        parcel.writeLong(identityCardId)
        parcel.writeString(deckname)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeckEntity> {
        override fun createFromParcel(parcel: Parcel): DeckEntity {
            return DeckEntity(parcel)
        }

        override fun newArray(size: Int): Array<DeckEntity?> {
            return arrayOfNulls(size)
        }
    }
}