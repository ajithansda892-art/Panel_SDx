package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.AppDatabase
import com.example.ui.screens.ConverterScreen
import com.example.ui.screens.GuideScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LayoutGuideScreen
import com.example.ui.screens.MasteryScreen
import com.example.ui.screens.OptimizerScreen
import com.example.ui.screens.TesterScreen
import com.example.ui.screens.VaultScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.SensViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SensViewModel(database.sensitivityDao()) as T
            }
        })[SensViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AppNavigation(viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: SensViewModel) {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    onNavigateToGuide = { navController.navigate("guide") },
                    onNavigateToTester = { navController.navigate("tester") },
                    onNavigateToVault = { navController.navigate("vault") },
                    onNavigateToLayouts = { navController.navigate("layouts") },
                    onNavigateToMastery = { navController.navigate("mastery") },
                    onNavigateToOptimizer = { navController.navigate("optimizer") },
                    onNavigateToConverter = { navController.navigate("converter") }
                )
            }
            composable("guide") {
                GuideScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("tester") {
                TesterScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("vault") {
                VaultScreen(viewModel = viewModel, onNavigateBack = { navController.popBackStack() })
            }
            composable("layouts") {
                LayoutGuideScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("mastery") {
                MasteryScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("optimizer") {
                OptimizerScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("converter") {
                ConverterScreen(onNavigateBack = { navController.popBackStack() })
            }
        }
    }
}
