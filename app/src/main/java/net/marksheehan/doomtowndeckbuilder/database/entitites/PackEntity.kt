package net.marksheehan.doomtowndeckbuilder.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PackEntity")
data class PackEntity(
        @PrimaryKey
        val packname: String,
        var isSelected: Boolean = false
)
{
    override fun toString(): String {
        return "$packname $isSelected"
    }
}