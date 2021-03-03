package com.uc3m.discography.api

import com.uc3m.discography.util.Constants.Companion.AUDIODB_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(AUDIODB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val theAudioDbAPI: TheAudioDbAPI by lazy {
        retrofit.create(TheAudioDbAPI::class.java)
    }
}