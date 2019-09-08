package net.marksheehan.doomtowndeckbuilder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName= "PackEntity")
data class PackEntity (
        @PrimaryKey
        val packname : String,
        var isSelected : Boolean = false
)