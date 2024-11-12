package com.example.randomanimalfacts.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomanimalfacts.R
import com.example.randomanimalfacts.data.UserDataUiEvents
import com.example.randomanimalfacts.ui.AnimalCard
import com.example.randomanimalfacts.ui.ButtonComponent
import com.example.randomanimalfacts.ui.TextComponent
import com.example.randomanimalfacts.ui.TextFieldComponent
import com.example.randomanimalfacts.ui.TopBar
import com.example.randomanimalfacts.ui.UserInputViewModel

@Composable
fun UserInputScreen(UserInputViewModel: UserInputViewModel, showWelocmeScreen: (valuespair:Pair<String,String>) -> Unit) {
    Surface (
        modifier = Modifier .fillMaxSize()){

        Column(modifier = Modifier.background(color = Color.DarkGray)
            .fillMaxSize()
            .padding(18.dp)) {
            Spacer(modifier = Modifier.size(28.dp))
            TopBar(value = "Hi how are you")
            Spacer(modifier = Modifier.size(20.dp))
            TextComponent(
                textvalue = "Are you ready to know some facts about animals??",
                textSize = 24.sp
            )
            Spacer(modifier = Modifier.size(20.dp))

            TextComponent(
                textvalue = "So tell us which animal you like ",
                textSize = 20.sp,
                colourvalue = Color.White
            )
            Spacer(modifier = Modifier.size(52.dp))

            TextComponent(textvalue = "Name", textSize = 18.sp, colourvalue = Color.White)
            Spacer(modifier = Modifier.size(8.dp))
            TextFieldComponent(onTextChanged = {
                UserInputViewModel.onEvent(
                    UserDataUiEvents.UserNameEntered(it)
                )
            })
            Spacer(modifier = Modifier.size(16.dp))
            TextComponent(
                textvalue = "Choose The Animal",
                textSize = 18.sp,
                colourvalue = Color.White,
            )

            Spacer(modifier = Modifier.size(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                AnimalCard(image = R.drawable.cat, animalSelected = {
                    UserInputViewModel.onEvent(UserDataUiEvents.AnimalSelected(it))

                }, selected = UserInputViewModel.uiState.value.animalSelected == "Cat")

                AnimalCard(image = R.drawable.dog, animalSelected = {
                    UserInputViewModel.onEvent(UserDataUiEvents.AnimalSelected(it))

                }, selected = UserInputViewModel.uiState.value.animalSelected == "Dog")

                AnimalCard(image = R.drawable.horce, animalSelected = {
                    UserInputViewModel.onEvent(UserDataUiEvents.AnimalSelected(it))

                }, selected = UserInputViewModel.uiState.value.animalSelected == "Horse")


            }
            Spacer(modifier = Modifier.size(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                AnimalCard(image = R.drawable.sheep, animalSelected = {
                    UserInputViewModel.onEvent(UserDataUiEvents.AnimalSelected(it))

                }, selected = UserInputViewModel.uiState.value.animalSelected == "Sheep")
                AnimalCard(image = R.drawable.fish, animalSelected = {
                    UserInputViewModel.onEvent(UserDataUiEvents.AnimalSelected(it))

                }, selected = UserInputViewModel.uiState.value.animalSelected == "Fish")
                AnimalCard(image = R.drawable.frog, animalSelected = {
                    UserInputViewModel.onEvent(UserDataUiEvents.AnimalSelected(it))
                }, selected = UserInputViewModel.uiState.value.animalSelected == "Frog ")
            }

                if (UserInputViewModel.isValidState())
                ButtonComponent(
                    goToDetailsScreen = {
                        println("======ComingHere")
                        println("======${UserInputViewModel.uiState.value.nameEntered} and ${UserInputViewModel.uiState.value.animalSelected}")

                        showWelocmeScreen(
                            Pair(
                                UserInputViewModel.uiState.value.nameEntered,
                                UserInputViewModel.uiState.value.animalSelected
                            )
                        )
                    }
                )

            }
        }

    
}
@PreviewLightDark
@Composable
fun UserInputScreenPreview(){
    UserInputScreen(UserInputViewModel()) {
        println("coming_to_welcome_screen")
        println(it.first)
        println(it.second)
    }
}