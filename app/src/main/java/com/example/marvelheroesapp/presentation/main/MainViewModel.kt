package com.example.marvelheroesapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroesapp.models.HeroData
import com.example.marvelheroesapp.network.repositories.HeroRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val heroRepository = HeroRepository()

    private val _heroes = MutableStateFlow<HeroData?>(null)
    val heroes: StateFlow<HeroData?> = _heroes

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        getHeroes()
    }

    private fun getHeroes() {
        val handler = CoroutineExceptionHandler { _, exception ->
            _errorState.value = exception.message
        }
        viewModelScope.launch(handler) {
            val heroes = async { heroRepository.getHeroes() }
            _heroes.update { heroes.await() }
        }
    }
}