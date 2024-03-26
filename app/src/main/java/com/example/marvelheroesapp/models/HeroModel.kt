package com.example.marvelheroesapp.models

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class HeroData(
    val code: Int,
    val status: String,
    val data: HeroResult,
)

@Keep
@JsonClass(generateAdapter = true)
data class HeroResult(
    val results: List<HeroCard>,
)

@Keep
@JsonClass(generateAdapter = true)
data class HeroCard(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
) {
    fun getImageUrl(): String {
        val basePath = thumbnail.path.replace("http", "https")
        val extension = thumbnail.extension
        return "$basePath.$extension"
    }
}

@Keep
@JsonClass(generateAdapter = true)
data class HeroThumbnail(
    val path: String,
    val extension: String,
)
