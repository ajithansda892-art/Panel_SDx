package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CyberBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(onNavigateBack: () -> Unit) {
    var fromSens by remember { mutableStateOf("50") }
    var fromDpi by remember { mutableStateOf("400") }
    var toDpi by remember { mutableStateOf("800") }
    var result by remember { mutableStateOf("25.0") }

    fun calculate() {
        val s = fromSens.toFloatOrNull() ?: 0f
        val d1 = fromDpi.toFloatOrNull() ?: 1f
        val d2 = toDpi.toFloatOrNull() ?: 1f
        result = String.format("%.2f", s * (d1 / d2))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sensitivity Converter") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "DPI Sensitivity Match",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = CyberBlue
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Maintain your muscle memory when switching DPI or screen resolution.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            OutlinedTextField(
                value = fromSens,
                onValueChange = { fromSens = it; calculate() },
                label = { Text("Original Sensitivity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = fromDpi,
                    onValueChange = { fromDpi = it; calculate() },
                    label = { Text("Old DPI") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = toDpi,
                    onValueChange = { toDpi = it; calculate() },
                    label = { Text("New DPI") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CyberBlue.copy(alpha = 0.1f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "CONVERTED SENSITIVITY", style = MaterialTheme.typography.labelMedium)
                    Text(
                        text = result,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = CyberBlue
                    )
                    Icon(Icons.Default.SyncAlt, contentDescription = null, tint = CyberBlue)
                }
            }
            
            Text(
                text = "Formula: (Old Sens) * (Old DPI / New DPI)",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}
