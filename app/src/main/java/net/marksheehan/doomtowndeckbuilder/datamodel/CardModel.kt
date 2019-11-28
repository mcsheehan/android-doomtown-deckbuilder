package net.marksheehan.doomtowndeckbuilder.datamodel

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import net.marksheehan.doomtowndeckbuilder.database.entitites.PackEntity

@Entity(tableName="CardModel",
        foreignKeys = [ForeignKey(entity = PackEntity::class,
                parentColumns = arrayOf("packname"),
                childColumns = arrayOf("pack"),
                onDelete = ForeignKey.SET_DEFAULT)])
class CardModel() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var cardId : Long = 0

    @SerializedName("last-modified")
    var lastModified: String? = null

    var code: String? = null

    var title: String? = null
    var type: String? = null

    var pack: String = ""

    @SerializedName("type_code")
    var typeCode: String? = null

    var suit: String? = null

    var keywords: String? = null
    var text: String? = null

    var cost: Long = 0

    var gang: String? = null
    @SerializedName("gang_code")
    var gang_code: String? = null
    var gang_letter: String? = null

    var number: Long = 0
    var quantity: Long = 0
    var rank: Long = 0
    var upkeep: Long = 0
    var bullets: Long = 0
    var influence: Long = 0
    var control: Long = 0
    var wealth: Long = 0

    var url: String? = null
    var imagesrc: String? = null

    fun getImagePath() : String{
        return getFilePath()
    }

    private fun getDownloadUrl() : String{
        val downloadUrl = "https://dtdb.co" + imagesrc!!
        return downloadUrl
    }

    private fun getFilePath() : String{
        val fileName : String = imagesrc!!.substringAfterLast("/")
        val filePath  ="file:///android_asset/cards/" + fileName
        return filePath
    }

    constructor(parcel: Parcel) : this() {
        lastModified = parcel.readString()
        code = parcel.readString()
        title = parcel.readString()
        type = parcel.readString()
        typeCode = parcel.readString()
        suit = parcel.readString()
        keywords = parcel.readString()
        text = parcel.readString()
        cost = parcel.readLong()
        gang = parcel.readString()
        gang_code = parcel.readString()
        gang_letter = parcel.readString()
        number = parcel.readLong()
        quantity = parcel.readLong()
        rank = parcel.readLong()
        upkeep = parcel.readLong()
        bullets = parcel.readLong()
        influence = parcel.readLong()
        control = parcel.readLong()
        wealth = parcel.readLong()
        url = parcel.readString()
        imagesrc = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lastModified)
        parcel.writeString(code)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(typeCode)
        parcel.writeString(suit)
        parcel.writeString(keywords)
        parcel.writeString(text)
        parcel.writeLong(cost)
        parcel.writeString(gang)
        parcel.writeString(gang_code)
        parcel.writeString(gang_letter)
        parcel.writeLong(number)
        parcel.writeLong(quantity)
        parcel.writeLong(rank)
        parcel.writeLong(upkeep)
        parcel.writeLong(bullets)
        parcel.writeLong(influence)
        parcel.writeLong(control)
        parcel.writeLong(wealth)
        parcel.writeString(url)
        parcel.writeString(imagesrc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardModel> {
        override fun createFromParcel(parcel: Parcel): CardModel {
            return CardModel(parcel)
        }

        override fun newArray(size: Int): Array<CardModel?> {
            return arrayOfNulls(size)
        }
    }
}
