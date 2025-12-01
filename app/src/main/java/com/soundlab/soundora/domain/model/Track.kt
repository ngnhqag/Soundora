package com.soundlab.soundora.domain.model

data class Track(
    val id: Long,                 // ID track
    val readable: Boolean,        // Có thể nghe online hay không
    val title: String,            // Tên đầy đủ
    val titleShort: String,       // Tên ngắn gọn
    val titleVersion: String,     // Phiên bản track (Remix, Live…)
    val link: String,             // Link chi tiết trên Deezer
    val duration: Long,           // Thời lượng (giây)
    val rank: Long,               // Xếp hạng theo lượt nghe
    val explicitLyrics: Boolean,  // Có lời NSFW không
    val explicitContentLyrics: Long, // Chỉ số explicit lyrics
    val explicitContentCover: Long,  // Chỉ số explicit cover
    val preview: String,          // Link preview 30s
    val md5Image: String,         // Hash hình ảnh album
    val artist: Artist,           // Thông tin nghệ sĩ
    val album: Album,             // Thông tin album
    val type: String              // Loại object ("track")
)
