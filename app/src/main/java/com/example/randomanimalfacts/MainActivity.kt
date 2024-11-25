package com.example.randomanimalfacts

import FirestoreFactsRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.randomanimalfacts.ui.FacstViewmodel
import com.example.randomanimalfacts.ui.screens.RandomAnimalFactsNavigationGraph
import com.example.randomanimalfacts.ui.theme.RandomAnimalFactsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private  var factsViewModel: FacstViewmodel = FacstViewmodel()
    private  var firebaseFActs: FirestoreFactsRepository=FirestoreFactsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       saveInitialFacts()
        enableEdgeToEdge()
        installSplashScreen()
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
    private fun saveInitialFacts() {
        lifecycleScope.launch {
            firebaseFActs.saveAnimalFacts("Dog", factsViewModel.getDogFacts())
            firebaseFActs.saveAnimalFacts("Cat", factsViewModel.getCatFacts())
            firebaseFActs.saveAnimalFacts("Horse", factsViewModel.getHorseFacts())
            firebaseFActs.saveAnimalFacts("Sheep", factsViewModel.getSheepFacts())
            firebaseFActs.saveAnimalFacts("Fish", factsViewModel.getFishFacts())
            firebaseFActs.saveAnimalFacts("Frog", factsViewModel.getFrogFacts())
        }
    }
}
