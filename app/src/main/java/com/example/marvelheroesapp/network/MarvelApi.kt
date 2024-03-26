package com.example.marvelheroesapp.network

import com.example.marvelheroesapp.models.HeroData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    suspend fun getHeroes(
        @Query("limit") limit: Long = 3,
        @Query("offset") offset: Long = 0
    ): HeroData

    @GET("characters/{characterId}")
    suspend fun getHero(
        @Path("characterId") characterId: Int,
    ): HeroData
}
