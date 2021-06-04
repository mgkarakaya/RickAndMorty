package com.mgk.melih_rickmorty.api

import com.mgk.melih_rickmorty.model.CharacterList
import com.mgk.melih_rickmorty.model.CharacterSingle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RmRetrofitService {

    @GET("character/")
    suspend fun getAllCharacters() : Response<CharacterList>

    @GET("character/")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): CharacterList

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterSingle>

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun create(): RmRetrofitService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RmRetrofitService::class.java)
        }
    }
}
