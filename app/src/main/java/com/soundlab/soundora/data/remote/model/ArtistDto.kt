package com.soundlab.soundora.data.remote.model

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    val id: Long,                                // ID duy nhất của nghệ sĩ
    val name: String,                            // Tên nghệ sĩ
    val link: String,                            // Link trang artist trên Deezer (web)
    val share: String,                           // Link chia sẻ (deep link / social)
    val picture: String,                         // Ảnh mặc định của nghệ sĩ
    @SerializedName("picture_small")
    val pictureSmall: String,                    // Ảnh kích thước nhỏ (56x56)
    @SerializedName("picture_medium")
    val pictureMedium: String,                   // Ảnh kích thước trung bình (250x250)
    @SerializedName("picture_big")
    val pictureBig: String,                      // Ảnh lớn (500x500)
    @SerializedName("picture_xl")
    val pictureXl: String,                       // Ảnh siêu lớn (1000x1000)
    @SerializedName("nb_album")
    val nbAlbum: Long,                           // Tổng số album của nghệ sĩ
    @SerializedName("nb_fan")
    val nbFan: Long,                             // Tổng số fan trên Deezer
    @SerializedName("tracklist")
    val trackList: String,                       // API endpoint để lấy danh sách bài hát của nghệ sĩ
    val type: String,                            // Loại object (thường là "artist")
)
