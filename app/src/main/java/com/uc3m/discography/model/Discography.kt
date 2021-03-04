package com.uc3m.discography.model


data class Discography(
        var album: List<Album>
)

data class Album(
    var strAlbum: String,
    var intYearReleased: String
)


