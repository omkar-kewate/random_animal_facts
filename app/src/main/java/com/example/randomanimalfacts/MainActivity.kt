package com.example.randomanimalfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.randomanimalfacts.ui.screens.RandomAnimalFactsNavigationGraph
import com.example.randomanimalfacts.ui.theme.RandomAnimalFactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomAnimalFactsTheme {
                RandomAnimalFactsaapp()

            }
        }
    }
    @Composable
    fun RandomAnimalFactsaapp(){
        RandomAnimalFactsNavigationGraph()
    }
}
