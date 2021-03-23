package com.uc3m.discography.api

import com.uc3m.discography.util.Constants.Companion.AUDIODB_URL
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{

        val certificatePinner  = CertificatePinner.Builder()
            .add("theaudiodb.com", "sha256/WuMDtalbDc8IU3aaWHk/nmZ26k367qB65+nfaU1GSzg=").build()

        val okHttpClient = OkHttpClient.Builder().certificatePinner(certificatePinner).build()

        Retrofit.Builder()
            .baseUrl(AUDIODB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val theAudioDbAPI: TheAudioDbAPI by lazy {
        retrofit.create(TheAudioDbAPI::class.java)
    }
}