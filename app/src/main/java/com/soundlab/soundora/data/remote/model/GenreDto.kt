package com.soundlab.soundora.data.remote.model

data class GenreDto(
    val id: Long,         // ID thể loại
    val name: String,     // Tên thể loại
    val picture: String,  // Ảnh đại diện của genre
    val type: String      // Luôn là "genre"
)
