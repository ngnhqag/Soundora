package com.soundlab.soundora.domain.model

data class Album(
    val id: Long,                 // ID album
    val title: String,            // Tên album
    val link: String,             // Link Deezer của album
    val share: String,            // Link chia sẻ
    val cover: String,            // Ảnh cover mặc định
    val coverSmall: String,       // Cover nhỏ
    val coverMedium: String,      // Cover trung bình
    val coverBig: String,         // Cover lớn
    val coverXl: String,          // Cover cực lớn
    val md5Image: String,         // Hash ảnh (dùng cache)
    val genreId: Long,            // ID thể loại chính
    val nbTracks: Long,           // Số lượng bài hát
    val duration: Long,           // Tổng thời lượng album
    val fans: Long,               // Số follower/fans
    val releaseDate: String,      // Ngày phát hành
    val recordType: String,       // Loại album (album, EP, single…)
    val trackList: String,        // API lấy danh sách track
    val explicitLyrics: Boolean,  // Có nội dung NSFW không
    val artist: Artist,           // Nghệ sĩ chính của album
    val type: String              // Luôn là "album"
)
