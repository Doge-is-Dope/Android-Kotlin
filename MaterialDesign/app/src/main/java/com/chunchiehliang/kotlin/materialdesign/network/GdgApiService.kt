package com.chunchiehliang.kotlin.materialdesign.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface GdgApiService {
    @GET("directory.json")
    suspend fun getChapters(): GdgResponse
}


object GdgApi {
    private const val BASE_URL = "https://developers.google.com/community/gdg/directory/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: GdgApiService by lazy {
        retrofit.create(GdgApiService::class.java)
    }
}
