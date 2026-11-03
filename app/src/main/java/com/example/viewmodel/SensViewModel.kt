package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SensitivityDao
import com.example.model.SensitivityPreset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SensViewModel(private val dao: SensitivityDao) : ViewModel() {

    val presets: StateFlow<List<SensitivityPreset>> = dao.getAllPresets()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun savePreset(preset: SensitivityPreset) {
        viewModelScope.launch {
            dao.insertPreset(preset)
        }
    }

    fun deletePreset(preset: SensitivityPreset) {
        viewModelScope.launch {
            dao.deletePreset(preset)
        }
    }
}
