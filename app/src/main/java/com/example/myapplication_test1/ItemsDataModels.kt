@file:OptIn(InternalSerializationApi::class)

package com.example.myapplication_test1

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val info: Info,
    val results: List<T>
)

@Serializable
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationReference,
    val location: LocationReference,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class LocationReference(
    val name: String,
    val url: String
)

// Обновленный ItemData для поддержки URL изображений
data class ItemData(
    val title: String,
    val subtitle: String,
    val imageUrl: String? = null,
    val imageRes: Int? = null,
    val type: String // "character" или "location"
)