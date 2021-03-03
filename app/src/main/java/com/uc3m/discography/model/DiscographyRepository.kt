package com.uc3m.discography.model

import com.uc3m.discography.api.RetrofitInstance
import retrofit2.Response

class DiscographyRepository {

    suspend fun getDiscography(artist: String): Response<Discography>{
        return RetrofitInstance.theAudioDbAPI.getDiscography(artist)
    }
}