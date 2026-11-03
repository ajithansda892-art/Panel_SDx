package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sensitivity_presets")
data class SensitivityPreset(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val game: String,
    val general: Int,
    val redDot: Int,
    val scope2x: Int,
    val scope4x: Int,
    val sniper: Int,
    val freeLook: Int,
    val createdAt: Long = System.currentTimeMillis()
)
