package com.soundlab.soundora.data.mapper

import com.soundlab.soundora.data.remote.model.ArtistDto
import com.soundlab.soundora.data.remote.model.TopAlbumDto
import com.soundlab.soundora.domain.model.Artist
import com.soundlab.soundora.domain.model.TopAlbum


fun ArtistDto.toDomain(): Artist {
    return Artist(
        id = this.id,
        name = this.name,
        link = this.link,
        share = this.share,
        picture = this.picture,
        pictureSmall = this.pictureSmall,
        pictureMedium = this.pictureMedium,
        pictureBig = this.pictureBig,
        pictureXl = this.pictureXl,
        nbAlbum = this.nbAlbum,
        nbFan = this.nbFan,
        trackList = this.trackList,
        type = this.type
    )
}

fun TopAlbumDto.toDomain(): TopAlbum {
    return TopAlbum(
        id = id,
        title = title,
        link = link,
        cover = cover,
        coverSmall = coverSmall,
        coverMedium = coverMedium,
        coverBig = coverBig,
        coverXl = coverXl,
        md5Image = md5Image,
        recordType = recordType,
        trackList = trackList,
        explicitLyrics = explicitLyrics,
        position = position,
        artistName = artist.name,
        type = type
    )
}