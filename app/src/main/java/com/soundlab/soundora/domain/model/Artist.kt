package com.soundlab.soundora.domain.model

data class Artist (
    val id: Long,                 // ID nghệ sĩ
    val name: String,             // Tên nghệ sĩ
    val link: String,             // Link artist trên Deezer
    val share: String,            // Link chia sẻ
    val picture: String,          // Ảnh mặc định
    val pictureSmall: String,     // Ảnh nhỏ
    val pictureMedium: String,    // Ảnh trung bình
    val pictureBig: String,       // Ảnh lớn
    val pictureXl: String,        // Ảnh cực lớn
    val nbAlbum: Long,            // Tổng số album
    val nbFan: Long,              // Tổng số fan
    val trackList: String,        // API endpoint track của nghệ sĩ
    val type: String              // Loại object ("artist")
)