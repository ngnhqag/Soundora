package com.soundlab.soundora.data.local.roomdatabase.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "albums",
    foreignKeys = [
        ForeignKey(
            entity = ArtistEntity::class,           // Liên kết đến ArtistEntity
            parentColumns = ["id"],                // Khóa chính của artist
            childColumns = ["artistId"],           // Khóa ngoại trong album
            onDelete = ForeignKey.CASCADE          // Khi artist xóa -> album cũng xóa
        )
    ]
)
data class AlbumEntity(
    @PrimaryKey val id: Long,                       // ID album
    val title: String,                              // Tên album
    val link: String,                               // Link chi tiết album trên Deezer
    val share: String,                              // Link chia sẻ
    val cover: String,                              // Ảnh cover mặc định
    val coverSmall: String,                         // Cover kích thước nhỏ
    val coverMedium: String,                        // Cover kích thước trung bình
    val coverBig: String,                           // Cover kích thước lớn
    val coverXl: String,                            // Cover cực lớn
    val md5Image: String,                           // Hash ảnh dùng để cache
    val genreId: Long,                              // ID thể loại chính của album
    val nbTracks: Long,                             // Số lượng track trong album
    val duration: Long,                             // Tổng thời lượng album (giây)
    val fans: Long,                                 // Số người theo dõi/fan
    val releaseDate: String,                        // Ngày phát hành (yyyy-mm-dd)
    val recordType: String,                         // Loại album (album, EP, single…)
    val trackList: String,                          // URL API lấy danh sách track
    val explicitLyrics: Boolean,                    // Album có nội dung NSFW hay không
    val artistId: Long,                             // Liên kết artist (khóa ngoại)
    val type: String                                // Loại object ("album")
)
