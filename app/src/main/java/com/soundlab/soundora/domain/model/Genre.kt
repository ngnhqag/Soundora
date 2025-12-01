package com.soundlab.soundora.domain.model

data class Genre(
    val id: Long,                 // ID thể loại
    val name: String,             // Tên thể loại
    val picture: String,          // Ảnh đại diện
    val type: String              // Luôn là "genre"
)
