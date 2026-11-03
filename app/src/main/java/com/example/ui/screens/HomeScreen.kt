package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.CyberBlue
import com.example.ui.theme.CyberPurple

@Composable
fun HomeScreen(
    onNavigateToGuide: () -> Unit,
    onNavigateToTester: () -> Unit,
    onNavigateToVault: () -> Unit,
    onNavigateToLayouts: () -> Unit,
    onNavigateToMastery: () -> Unit,
    onNavigateToOptimizer: () -> Unit,
    onNavigateToConverter: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            BannerSection()
        }

        item {
            Text(
                text = "Elite Tools",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            ToolCard(
                title = "Sensitivity Guide",
                description = "Pro settings for top competitive games.",
                icon = Icons.Default.SettingsSuggest,
                color = CyberBlue,
                onClick = onNavigateToGuide
            )
        }

        item {
            ToolCard(
                title = "Touch Optimizer",
                description = "Test and optimize your screen response.",
                icon = Icons.Default.TouchApp,
                color = CyberPurple,
                onClick = onNavigateToTester
            )
        }

        item {
            ToolCard(
                title = "HUD Layouts",
                description = "Pro button placements for speed.",
                icon = Icons.Default.Gamepad,
                color = Color(0xFFFFD600),
                onClick = onNavigateToLayouts
            )
        }

        item {
            ToolCard(
                title = "Mastery Tips",
                description = "Techniques for headshots and precision.",
                icon = Icons.Default.TipsAndUpdates,
                color = Color(0xFFFF5722),
                onClick = onNavigateToMastery
            )
        }

        item {
            ToolCard(
                title = "Device Optimizer",
                description = "System calibration for zero lag.",
                icon = Icons.Default.Speed,
                color = Color(0xFF00B0FF),
                onClick = onNavigateToOptimizer
            )
        }

        item {
            ToolCard(
                title = "Sens Converter",
                description = "Match muscle memory across DPIs.",
                icon = Icons.Default.SyncAlt,
                color = Color(0xFFE91E63),
                onClick = onNavigateToConverter
            )
        }

        item {
            ToolCard(
                title = "Settings Vault",
                description = "Store and manage your personal configs.",
                icon = Icons.Default.Storage,
                color = Color(0xFF76FF03),
                onClick = onNavigateToVault
            )
        }
    }
}

@Composable
fun BannerSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CyberBlue.copy(alpha = 0.3f), Color.Transparent)
                )
            )
    ) {
        // Placeholder for the generated image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("file:///android_asset/banner_gamer.jpg")
                .crossfade(true)
                .build(),
            contentDescription = "Banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "ELITE SENS GUIDE",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(
                text = "Master your control",
                style = MaterialTheme.typography.bodyMedium,
                color = CyberBlue
            )
        }
    }
}

@Composable
fun ToolCard(
    title: String,
    description: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.weight(1.0f))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
