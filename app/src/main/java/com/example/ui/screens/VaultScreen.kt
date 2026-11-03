package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.SensitivityPreset
import com.example.ui.theme.CyberBlue
import com.example.ui.theme.CyberPurple
import com.example.viewmodel.SensViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaultScreen(
    viewModel: SensViewModel,
    onNavigateBack: () -> Unit
) {
    val presets by viewModel.presets.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings Vault") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = CyberBlue
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Preset")
            }
        }
    ) { padding ->
        if (presets.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No presets saved yet.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(presets) { preset ->
                    PresetCard(preset, onDelete = { viewModel.deletePreset(preset) })
                }
            }
        }

        if (showAddDialog) {
            AddPresetDialog(
                onDismiss = { showAddDialog = false },
                onSave = { preset ->
                    viewModel.savePreset(preset)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
fun PresetCard(preset: SensitivityPreset, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = preset.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = preset.game,
                        style = MaterialTheme.typography.bodySmall,
                        color = CyberPurple
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SensGrid(preset)
        }
    }
}

@Composable
fun SensGrid(preset: SensitivityPreset) {
    Column {
        Row(Modifier.fillMaxWidth()) {
            SensGridItem("Gen", preset.general, Modifier.weight(1f))
            SensGridItem("Red", preset.redDot, Modifier.weight(1f))
            SensGridItem("2x", preset.scope2x, Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth()) {
            SensGridItem("4x", preset.scope4x, Modifier.weight(1f))
            SensGridItem("Snip", preset.sniper, Modifier.weight(1f))
            SensGridItem("Free", preset.freeLook, Modifier.weight(1f))
        }
    }
}

@Composable
fun SensGridItem(label: String, value: Int, modifier: Modifier) {
    Column(modifier = modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = value.toString(), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AddPresetDialog(onDismiss: () -> Unit, onSave: (SensitivityPreset) -> Unit) {
    var name by remember { mutableStateOf("") }
    var game by remember { mutableStateOf("") }
    var general by remember { mutableStateOf("80") }
    // ... Simplified for the example, just adding name and game
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Save Preset") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = game, onValueChange = { game = it }, label = { Text("Game") })
                // In a real app, you'd add sliders for each sensitivity value
                Text("Default sensitivity values will be saved.", style = MaterialTheme.typography.labelSmall)
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(
                    SensitivityPreset(
                        name = name,
                        game = game,
                        general = general.toIntOrNull() ?: 80,
                        redDot = 75,
                        scope2x = 70,
                        scope4x = 60,
                        sniper = 50,
                        freeLook = 100
                    )
                )
            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
