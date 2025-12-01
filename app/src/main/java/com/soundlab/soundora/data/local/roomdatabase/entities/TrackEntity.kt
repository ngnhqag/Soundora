package com.soundlab.soundora.data.local.roomdatabase.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tracks",
    foreignKeys = [
        ForeignKey(
            entity = ArtistEntity::class,           // Liên kết đến nghệ sĩ
            parentColumns = ["id"],                // Khóa chính của artist
            childColumns = ["artistId"],           // Khóa ngoại trong track
            onDelete = ForeignKey.CASCADE          // Xóa track khi artist bị xóa
        ),
        ForeignKey(
            entity = AlbumEntity::class,            // Liên kết đến album
            parentColumns = ["id"],                // Khóa chính album
            childColumns = ["albumId"],            // Khóa ngoại trong track
            onDelete = ForeignKey.CASCADE          // Xóa track khi album bị xóa
        )
    ]
)
data class TrackEntity(
    @PrimaryKey val id: Long,                       // ID track
    val readable: Boolean,                          // Track có thể nghe trực tuyến hay không
    val title: String,                              // Tên track đầy đủ
    val titleShort: String,                         // Tên track ngắn gọn
    val titleVersion: String,                       // Phiên bản track (Remix, Live…)
    val link: String,                               // Link chi tiết track trên Deezer
    val duration: Long,                             // Thời lượng track (giây)
    val rank: Long,                                 // Xếp hạng track dựa trên lượt nghe
    val explicitLyrics: Boolean,                    // Track có lời nhạc NSFW hay không
    val explicitContentLyrics: Long,                // Chỉ số explicit lyrics
    val explicitContentCover: Long,                 // Chỉ số explicit cover
    val preview: String,                            // Link preview 30s
    val md5Image: String,                           // Hash hình ảnh album
    val artistId: Long,                             // Liên kết artist (khóa ngoại)
    val albumId: Long,                              // Liên kết album (khóa ngoại)
    val type: String                                // Loại object ("track")
)