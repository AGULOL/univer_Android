package com.example.myapplication_test1


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RickAndMortyDao {

    // Characters operations
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()

    // Locations operations
    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationEntity>)

    @Query("DELETE FROM locations")
    suspend fun clearLocations()

    // Combined data for UI
    @Query("SELECT COUNT(*) FROM characters")
    suspend fun getCharactersCount(): Int

    @Query("SELECT COUNT(*) FROM locations")
    suspend fun getLocationsCount(): Int
}