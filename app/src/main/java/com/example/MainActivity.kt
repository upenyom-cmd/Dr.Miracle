package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.data.database.MedicalDatabase
import com.example.data.repository.MedicalRepository
import com.example.ui.ConditionDetailScreen
import com.example.ui.MainAppScreen
import com.example.ui.MedicalViewModel
import com.example.ui.MedicalViewModelFactory
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize database and repository
        val database = MedicalDatabase.getDatabase(applicationContext)
        val repository = MedicalRepository(database.medicalDao())
        
        // Instantiate ViewModel
        val viewModel = ViewModelProvider(
            this,
            MedicalViewModelFactory(repository)
        )[MedicalViewModel::class.java]

        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf("MAIN") }

                    BackHandler(enabled = currentScreen == "DETAIL") {
                        currentScreen = "MAIN"
                    }

                    Crossfade(
                        targetState = currentScreen,
                        label = "screen_navigation"
                    ) { screen ->
                        when (screen) {
                            "MAIN" -> {
                                MainAppScreen(
                                    viewModel = viewModel,
                                    onNavigateToDetail = { currentScreen = "DETAIL" }
                                )
                            }
                            "DETAIL" -> {
                                ConditionDetailScreen(
                                    viewModel = viewModel,
                                    onNavigateBack = { currentScreen = "MAIN" }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
