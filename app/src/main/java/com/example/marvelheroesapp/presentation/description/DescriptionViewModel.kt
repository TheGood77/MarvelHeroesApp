package com.example.marvelheroesapp.presentation.description

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

class DescriptionViewModel : ViewModel() {

    private val heroRepository = HeroRepository()

    private val _hero = MutableStateFlow<HeroData?>(null)
    val hero: StateFlow<HeroData?> = _hero

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun getHeroById(heroId: Int) {
        val handler = CoroutineExceptionHandler { _, exception ->
            _errorState.value = exception.message
        }
        viewModelScope.launch(handler) {
            val heroes = async { heroRepository.getHero(heroId) }
            _hero.update { heroes.await() }
        }
    }
}
