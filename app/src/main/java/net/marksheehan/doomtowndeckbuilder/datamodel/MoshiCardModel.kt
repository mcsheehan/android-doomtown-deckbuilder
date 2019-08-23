package net.marksheehan.doomtowndeckbuilder.datamodel

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiCardModel (
        // @field:
        @Json(name="last-modified")
        val lastModified: String? = null,

        @Json(name="code")
        val code: String?,

        val title: String?,
        val type: String?,

        @field:Json(name="type_code")
        val typeCode: String?,

        val suit: String?,
        val keywords: String?,
        val text: String?,
        val cost: Long? = 0,
        val gang: String?,

        @field:Json(name = "gang_code")
        val gang_code : String?,

        val gang_letter: String?,
        val number: Long? = 0,
        val quantity: Long? = 0,
        val rank: Long? = 0,
        val upkeep: Long? = 0,
        val bullets: Long? = 0,
        val influence: Long? = 0,
        val control: Long? = 0,
        val wealth: Long? = 0,
        val url: String?,
        val imagesrc: String?
)
