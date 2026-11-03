package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CyberBlue
import com.example.ui.theme.CyberPurple
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TesterScreen(onNavigateBack: () -> Unit) {
    var score by remember { mutableIntStateOf(0) }
    var lastReactionTime by remember { mutableLongStateOf(0L) }
    var targetActive by remember { mutableStateOf(false) }
    var targetPosition by remember { mutableStateOf(Offset(0.5f, 0.5f)) }
    var startTime by remember { mutableLongStateOf(0L) }
    var message by remember { mutableStateOf("Tap the target to start!") }

    LaunchedEffect(targetActive) {
        if (!targetActive) {
            message = "Wait for it..."
            delay(Random.nextLong(1000, 3000))
            targetPosition = Offset(Random.nextFloat(), Random.nextFloat())
            startTime = System.currentTimeMillis()
            targetActive = true
            message = "TAP!"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Touch Optimizer") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatBox("Score", score.toString(), CyberBlue)
                    StatBox("Reaction", "${lastReactionTime}ms", CyberPurple)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = message, style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            if (targetActive) {
                                lastReactionTime = System.currentTimeMillis() - startTime
                                score++
                                targetActive = false
                            }
                        }
                    }
            ) {
                if (targetActive) {
                    Box(
                        modifier = Modifier
                            .offset(
                                x = (targetPosition.x * (maxWidth - 60.dp).value).dp,
                                y = (targetPosition.y * (maxHeight - 60.dp).value).dp
                            )
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(CyberBlue)
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    lastReactionTime = System.currentTimeMillis() - startTime
                                    score++
                                    targetActive = false
                                }
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    score = 0
                    lastReactionTime = 0
                    targetActive = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reset Test")
            }
        }
    }
}

data class Offset(val x: Float, val y: Float)

@Composable
fun StatBox(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = color)
        Text(text = value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
    }
}
