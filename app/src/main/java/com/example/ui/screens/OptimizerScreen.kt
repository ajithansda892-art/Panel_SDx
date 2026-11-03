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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CyberBlue

data class OptiStep(
    val title: String,
    val description: String,
    val icon: ImageVector,
    var isDone: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptimizerScreen(onNavigateBack: () -> Unit) {
    var steps by remember {
        mutableStateOf(
            listOf(
                OptiStep("Enable Developer Options", "Go to Settings > About Phone > Tap 'Build Number' 7 times until you see a success message.", Icons.Default.Build),
                OptiStep("Optimize Smallest Width (DPI)", "In Developer Options, find 'Smallest Width'. Set it between 411 and 500 for better screen sensitivity.", Icons.Default.Speed),
                OptiStep("Reduce Animation Scales", "Set 'Window', 'Transition', and 'Animator' scales to 0.5x or Off for instant UI feel.", Icons.Default.Animation),
                OptiStep("Touch & Hold Delay", "In Accessibility > Physical, set 'Touch & Hold Delay' to Short (0.5s) for faster tapping.", Icons.Default.TouchApp),
                OptiStep("Background Process Limit", "Set to 'At most 2 processes' to focus CPU power on your active game.", Icons.Default.Memory),
                OptiStep("Disable HW Overlays", "Enable 'Disable HW Overlays' in Developer Options to use the GPU always for screen compositing.", Icons.Default.Dns)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Device Optimizer") },
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
                    text = "Optimization Checklist",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = CyberBlue
                )
                Text(
                    text = "Follow these steps in your Android system settings to achieve zero lag and perfect touch response.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            items(steps) { step ->
                OptiStepCard(
                    step = step,
                    onToggle = {
                        steps = steps.map { if (it.title == step.title) it.copy(isDone = !it.isDone) else it }
                    }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun OptiStepCard(step: OptiStep, onToggle: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (step.isDone) CyberBlue.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        if (step.isDone) Color.Gray.copy(alpha = 0.1f) else CyberBlue.copy(alpha = 0.1f),
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = step.icon,
                    contentDescription = null,
                    tint = if (step.isDone) Color.Gray else CyberBlue,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = step.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (step.isDone) Color.Gray else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = step.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (step.isDone) Color.Gray else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Checkbox(
                checked = step.isDone,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = CyberBlue)
            )
        }
    }
}
