package com.soundlab.soundora.data.remote.model

import com.google.gson.annotations.SerializedName

data class TrackDto(
    val id: Long,            // ID track
    val readable: Boolean,   // Track có thể nghe được trực tuyến hay không
    val title: String,       // Tên track đầy đủ
    @SerializedName("title_short")
    val titleShort: String,  // Tên track ngắn gọn
    @SerializedName("title_version")
    val titleVersion: String,// Phiên bản track (Remix, Live…)
    val link: String,        // Link chi tiết track trên Deezer
    val duration: Long,      // Thời lượng track (giây)
    val rank: Long,          // Xếp hạng track dựa trên lượt nghe
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,   // Track có lời nhạc NSFW hay không
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Long, // Chỉ số explicit lyrics
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Long,  // Chỉ số explicit cover
    val preview: String,     // Link preview 30s
    @SerializedName("md5_image")
    val md5Image: String,    // Hash hình ảnh album
    val artist: ArtistDto,      // Thông tin nghệ sĩ
    val album: AlbumDto,        // Thông tin album
    val type: String         // Loại object, thường là "track"
)
