package com.example.marvelheroesapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroesapp.models.HeroData
import com.example.marvelheroesapp.network.repositories.HeroRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel() {

    private val heroRepository = HeroRepository()

    private val _heroes = MutableStateFlow<HeroData?>(null)
    val heroes: StateFlow<HeroData?> = _heroes

    init {
        runBlocking { getHeroes() }
    }

    private suspend fun getHeroes() = viewModelScope.launch {
        val heroes = async { heroRepository.getHeroes() }
        _heroes.update { heroes.await() }
    }
}