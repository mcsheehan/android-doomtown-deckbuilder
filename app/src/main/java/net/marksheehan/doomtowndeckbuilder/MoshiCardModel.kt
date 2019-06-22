package net.marksheehan.doomtowndeckbuilder

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiCardModel (
        @field:Json(name="last-modified") val lastModified: String?,
        val code: String?,
        val title: String?,
        val type: String?,
        @field:Json(name="type_code") val typeCode: String?,
        val suit: String?,
        val keywords: String?,
        val text: String?,
        val cost: Long?,
        val gang: String?,
        @field:Json(name = "gang_code") val gang_code : String?,
        val gang_letter: String?,
        val number: Long?,
        val quantity: Long?,
        val rank: Long?,
        val upkeep: Long?,
        val bullets: Long?,
        val influence: Long?,
        val control: Long?,
        val wealth: Long?,
        val url: String,
        val imagesrc: String
)
