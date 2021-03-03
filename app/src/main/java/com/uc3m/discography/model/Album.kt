package com.uc3m.discography.model

data class Album(
    var strAlbum: String,
    var intYearReleased: String
)

data class Discography(
    var album: Album
)
