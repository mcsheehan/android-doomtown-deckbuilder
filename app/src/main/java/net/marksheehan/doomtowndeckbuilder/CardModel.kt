package net.marksheehan.doomtowndeckbuilder

import com.google.gson.annotations.SerializedName

class CardModel {
    @SerializedName("last-modified")
    var lastModified: String? = null

    var code: String? = null
    var title: String? = null
    var type: String? = null

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
}
