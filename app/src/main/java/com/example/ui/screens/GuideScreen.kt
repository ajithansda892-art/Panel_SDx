package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CyberBlue

data class GameSens(
    val gameName: String,
    val general: Int,
    val redDot: Int,
    val scope2x: Int,
    val scope4x: Int,
    val sniper: Int
)

val professionalSettings = listOf(
    GameSens("Battle Royale X", 95, 80, 75, 65, 50),
    GameSens("Mobile Ops", 85, 90, 80, 70, 45),
    GameSens("Tactical Strike", 90, 85, 70, 60, 55),
    GameSens("Fast Pace Arena", 100, 95, 85, 75, 60)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pro Sensitivity Guide") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                InfoCard()
            }

            items(professionalSettings) { sens ->
                SensGuideCard(sens)
            }
        }
    }
}

@Composable
fun InfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CyberBlue.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Info, contentDescription = null, tint = CyberBlue)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "These settings are recommendations based on top professional players. Adjust them according to your device DPI.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SensGuideCard(sens: GameSens) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = sens.gameName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = CyberBlue
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            SensRow("General", sens.general)
            SensRow("Red Dot", sens.redDot)
            SensRow("2x Scope", sens.scope2x)
            SensRow("4x Scope", sens.scope4x)
            SensRow("Sniper", sens.sniper)
        }
    }
}

@Composable
fun SensRow(label: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator(
                progress = { value / 100f },
                modifier = Modifier
                    .width(100.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = CyberBlue,
                trackColor = Color.Gray.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
