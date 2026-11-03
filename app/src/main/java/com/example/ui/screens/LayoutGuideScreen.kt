package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.theme.CyberBlue
import com.example.ui.theme.CyberPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutGuideScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pro HUD Layouts") },
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
                    text = "Master Your Grip",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = CyberBlue
                )
                Text(
                    text = "Optimizing your button placement is key to faster response times and better precision.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            item {
                LayoutCard(
                    title = "4-Finger Claw (Pro)",
                    description = "Used by 90% of professional competitive players for maximum agility.",
                    assetName = "hud_layout_claw.jpg"
                )
            }

            item {
                LayoutCard(
                    title = "3-Finger Hybrid",
                    description = "A balance between comfort and speed. Great for intermediate players.",
                    assetName = "hud_layout_hybrid.jpg" // Placeholder or reuse
                )
            }
        }
    }
}

@Composable
fun LayoutCard(title: String, description: String, assetName: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.Black)
            ) {
                AsyncImage(
                    model = "file:///android_asset/$assetName",
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
