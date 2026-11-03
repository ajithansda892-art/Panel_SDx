package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CyberBlue

data class MasteryTip(
    val title: String,
    val description: String
)

val masteryTips = listOf(
    MasteryTip("Headshot Precision", "Keep your crosshair at head level while moving to minimize the distance you need to flick when an enemy appears."),
    MasteryTip("Recoil Control", "Pull down your fire button slightly during sustained fire to compensate for vertical kick and keep your shots on target."),
    MasteryTip("Strafe Shooting", "Move left and right while shooting to become a harder target. Practice 'counter-strafing' for instant accuracy."),
    MasteryTip("Positioning", "Always stay near cover. Avoid open fields and use high ground to get a better angle on enemies."),
    MasteryTip("Trigger Discipline", "Don't just spray. Use short bursts at long range to maintain accuracy and save ammunition.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MasteryScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Mastery Tips") },
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
                Text(
                    text = "Professional Techniques",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = CyberBlue
                )
                Text(
                    text = "Master these skills to dominate your matches without needing shortcuts.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            items(masteryTips) { tip ->
                TipCard(tip)
            }
        }
    }
}

@Composable
fun TipCard(tip: MasteryTip) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.TipsAndUpdates,
                contentDescription = null,
                tint = CyberBlue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
