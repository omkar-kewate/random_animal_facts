package com.example.randomanimalfacts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomanimalfacts.ui.FacstViewmodel
import com.example.randomanimalfacts.ui.FactComposable
import com.example.randomanimalfacts.ui.TextComponent
import com.example.randomanimalfacts.ui.TopBar

@Composable
fun WelcomeScreen(username: String?, animalselected: String?) {
    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column (modifier = Modifier .background(color = Color.DarkGray)
            .fillMaxSize().
            padding(18.dp)){
            Spacer(modifier = Modifier.size(20.dp))
            TopBar(value = "Welcome to facts $username")
            Spacer(modifier = Modifier.size(100.dp))
            TextComponent(textvalue ="Lets see what You like" , textSize =24.sp )
            Spacer(modifier = Modifier.size(60.dp))
            TextComponent(textvalue = "So you are a $animalselected lover", textSize = 24.sp, colourvalue = Color.White)
            val factsViewModel : FacstViewmodel = viewModel()

            FactComposable(value = factsViewModel.generateRandomFact(animalselected!!))

        }

    }
}
@Preview
@Composable
fun  WelcomScreenPreview() {
    WelcomeScreen("username", "animalselected")

}