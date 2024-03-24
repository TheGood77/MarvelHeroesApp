package com.example.marvelheroesapp.network.repositories

import com.example.marvelheroesapp.models.HeroData
import com.example.marvelheroesapp.network.MarvelApiClient

class HeroRepository {

    private val api = MarvelApiClient
    suspend fun getHeroes(): HeroData {
        return api.marvelApi.getHeroes()
    }

    suspend fun getHero(
        characterId: Int,
    ): HeroData {
        return api.marvelApi.getHero(
            characterId = characterId,
        )
    }
}
