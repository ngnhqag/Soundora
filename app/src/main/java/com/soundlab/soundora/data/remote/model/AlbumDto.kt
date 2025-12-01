package com.soundlab.soundora.data.remote.model

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    val id: Long,                 // ID album
    val title: String,            // Tên album
    val link: String,             // Link Deezer của album
    val share: String,            // Link chia sẻ
    val cover: String,            // Ảnh cover mặc định
    @SerializedName("cover_small")
    val coverSmall: String,       // Cover nhỏ
    @SerializedName("cover_medium")
    val coverMedium: String,      // Cover trung bình
    @SerializedName("cover_big")
    val coverBig: String,         // Cover lớn
    @SerializedName("cover_xl")
    val coverXl: String,          // Cover cực lớn
    @SerializedName("md5_image")
    val md5Image: String,         // Hash ảnh (dùng để cache)
    @SerializedName("genre_id")
    val genreId: Long,            // ID thể loại chính
    @SerializedName("nb_tracks")
    val nbTracks: Long,           // Số lượng bài hát
    val duration: Long,           // Tổng thời lượng album
    val fans: Long,               // Số follower/fans
    @SerializedName("release_date")
    val releaseDate: String,      // Ngày phát hành
    @SerializedName("record_type")
    val recordType: String,       // Loại album (album, EP, single…)
    @SerializedName("tracklist")
    val trackList: String,        // API lấy danh sách track (URL)
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,  // Album có nội dung NSFW hay không
    val artist: ArtistDto,  // Nghệ sĩ chính của album
    val type: String,            // Luôn là "album"
)