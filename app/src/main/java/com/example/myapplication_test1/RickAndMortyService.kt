package com.example.myapplication_test1

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RickAndMortyService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCharacters(ids: List<Int>): List<Character> {
        val idsString = ids.joinToString(",")
        val response: List<Character> = client
            .get("https://rickandmortyapi.com/api/character/$idsString")
            .body()

        println("Response: ${response.joinToString()}")
        return response
    }

    suspend fun getLocations(ids: List<Int>): List<Location> {
        val idsString = ids.joinToString(",")
        val response: List<Location> = client
            .get("https://rickandmortyapi.com/api/location/$idsString")
            .body()
        return response
    }

    fun close() {
        client.close()
    }
}