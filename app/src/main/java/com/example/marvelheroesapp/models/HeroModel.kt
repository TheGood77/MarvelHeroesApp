package com.example.marvelheroesapp.models

import androidx.annotation.StringRes
import com.example.marvelheroesapp.R

data class HeroModel(
    val heroImageUrl: String,
    @StringRes val heroName: Int,
    @StringRes val heroDesc: Int,
) {
    companion object {
        val mockHeroList = listOf(
            HeroModel(
                heroImageUrl = "https://iili.io/JMnAfIV.png",
                heroName = R.string.first_hero_name,
                heroDesc = R.string.first_hero_text
            ),
            HeroModel(
                heroImageUrl = "https://iili.io/JMnuDI2.png",
                heroName = R.string.second_hero_name,
                heroDesc = R.string.second_hero_text
            ),
            HeroModel(
                heroImageUrl = "https://iili.io/JMnuyB9.png",
                heroName = R.string.third_hero_name,
                heroDesc = R.string.third_hero_text
            ),
        )
    }
}
