package com.uc3m.discography.api

import com.uc3m.discography.model.Discography
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TheAudioDbAPI {

    @GET("api/v1/json/1/discography.php?s={artist}")
    suspend fun getDiscography(@Path("artist") artist: String): Response<Discography>

}