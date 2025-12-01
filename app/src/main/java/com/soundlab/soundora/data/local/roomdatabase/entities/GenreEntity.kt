package com.soundlab.soundora.data.local.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val id: Long,                       // ID thể loại
    val name: String,                               // Tên thể loại
    val picture: String,                            // Ảnh đại diện của genre
    val type: String                                // Luôn là "genre"
)
