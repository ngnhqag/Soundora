package com.soundlab.soundora.data.mapper

import com.soundlab.soundora.data.remote.model.AlbumDto
import com.soundlab.soundora.data.remote.model.ArtistDto
import com.soundlab.soundora.data.remote.model.TopAlbumDto
import com.soundlab.soundora.data.remote.model.TrackDto
import com.soundlab.soundora.domain.model.Album
import com.soundlab.soundora.domain.model.Artist
import com.soundlab.soundora.domain.model.TopAlbum
import com.soundlab.soundora.domain.model.Track


fun ArtistDto.toDomain(): Artist {
    return Artist(
        id = id ?: 0L,
        name = name ?: "Unknown",
        link = link ?: "",
        share = share ?: "",
        picture = picture ?: "",
        pictureSmall = pictureSmall ?: "",
        pictureMedium = pictureMedium ?: "",
        pictureBig = pictureBig ?: "",
        pictureXl = pictureXl ?: "",
        nbAlbum = nbAlbum ?: 0L,
        nbFan = nbFan ?: 0L,
        trackList = trackList ?: "",
        type = type ?: "artist"
    )
}

fun AlbumDto.toDomain(): Album {
    return Album(
        id = id,
        title = title,
        link = link,
        share = share,
        cover = cover,
        coverSmall = coverSmall,
        coverMedium = coverMedium,
        coverBig = coverBig,
        coverXl = coverXl,
        md5Image = md5Image,
        genreId = genreId,
        nbTracks = nbTracks,
        duration = duration,
        fans = fans,
        releaseDate = releaseDate,
        recordType = recordType,
        trackList = trackList,
        explicitLyrics = explicitLyrics,
        artist = artist.toDomain(),   // mapper ArtistDto → Artist
        type = type
    )
}

fun TopAlbumDto.toDomain(): TopAlbum {
    return TopAlbum(
        id = id ?: 0L,
        title = title ?: "",
        link = link ?: "",
        cover = cover ?: "",
        coverSmall = coverSmall ?: "",
        coverMedium = coverMedium ?: "",
        coverBig = coverBig ?: "",
        coverXl = coverXl ?: "",
        md5Image = md5Image ?: "",
        recordType = recordType ?: "",
        trackList = trackList ?: "",
        explicitLyrics = explicitLyrics ?: false,
        position = position ?: 0L,
        artistName = artist?.name ?: "",
        type = type ?: ""
    )
}

fun TrackDto.toDomain(): Track {
    return Track(
        id = id,
        readable = readable,
        title = title,
        titleShort = titleShort,
        titleVersion = titleVersion,
        link = link,
        duration = duration,
        rank = rank,
        explicitLyrics = explicitLyrics,
        explicitContentLyrics = explicitContentLyrics,
        explicitContentCover = explicitContentCover,
        preview = preview,
        md5Image = md5Image,
        artist = artist?.toDomain() ?: Artist(),
        album = album?.toDomain() ?: Album(),
        type = type
    )
}