package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.SensitivityPreset
import kotlinx.coroutines.flow.Flow

@Dao
interface SensitivityDao {
    @Query("SELECT * FROM sensitivity_presets ORDER BY createdAt DESC")
    fun getAllPresets(): Flow<List<SensitivityPreset>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreset(preset: SensitivityPreset)

    @Delete
    suspend fun deletePreset(preset: SensitivityPreset)
}
