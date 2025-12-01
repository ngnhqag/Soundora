package com.soundlab.soundora.data.local.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey val id: Long,                       // ID nghệ sĩ
    val name: String,                               // Tên nghệ sĩ
    val link: String,                               // Link Deezer
    val share: String,                              // Link chia sẻ
    val picture: String,                            // Ảnh mặc định
    val pictureSmall: String,                       // Ảnh nhỏ
    val pictureMedium: String,                      // Ảnh trung bình
    val pictureBig: String,                         // Ảnh lớn
    val pictureXl: String,                          // Ảnh cực lớn
    val nbAlbum: Long,                              // Tổng số album
    val nbFan: Long,                                // Tổng fan
    val trackList: String,                          // API track của nghệ sĩ
    val type: String                                // Loại object
)
