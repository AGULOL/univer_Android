package com.example.myapplication_test1

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class RickAndMortyRepository(
    private val apiService: RickAndMortyService,
    private val dao: RickAndMortyDao
) {

    companion object {
        private const val CACHE_TIMEOUT = 30 * 60 * 1000 // 30 minutes
    }

    // Получаем комбинированные данные для UI
    fun getCombinedData(): Flow<List<ItemData>> {
        val charactersFlow = dao.getAllCharacters().map { characters ->
            characters.map { character ->
                ItemData(
                    title = character.name,
                    subtitle = "${character.species} - ${character.status}",
                    imageUrl = character.image,
                    type = ItemType.CHARACTER
                )
            }
        }

        val locationsFlow = dao.getAllLocations().map { locations ->
            locations.map { location ->
                ItemData(
                    title = location.name,
                    subtitle = "${location.type} - ${location.dimension}",
                    imageRes = R.drawable.ic_bank,
                    type = ItemType.LOCATION
                )
            }
        }

        return charactersFlow.combine(locationsFlow) { characters, locations ->
            (characters + locations).shuffled()
        }
    }

    // Загружаем и сохраняем данные из сети
    suspend fun refreshData() {
        try {
            // Загружаем данные из сети
            val characters = apiService.getCharacters(listOf(1, 2, 3, 4, 5, 27))
            val locations = apiService.getLocations(listOf(1, 2, 3, 4, 10))

            // Конвертируем в Entity и сохраняем в БД
            dao.insertCharacters(characters.map { it.toCharacterEntity() })
            dao.insertLocations(locations.map { it.toLocationEntity() })

        } catch (e: Exception) {
            throw e // Пробрасываем ошибку для обработки в ViewModel
        }
    }

    // Проверяем, нужно ли обновлять данные
    suspend fun shouldRefreshData(): Boolean {
        val characterCount = dao.getCharactersCount()
        val locationCount = dao.getLocationsCount()
        return characterCount == 0 || locationCount == 0
    }
}

// Extension functions для конвертации
fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        url = url,
        created = created
    )
}

fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        url = url,
        created = created
    )
}