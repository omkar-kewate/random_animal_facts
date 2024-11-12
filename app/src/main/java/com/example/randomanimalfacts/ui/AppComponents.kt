package com.example.randomanimalfacts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomanimalfacts.R

@Composable
fun TopBar(value:String) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = value,
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal, )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier =Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.logo) ,
            contentDescription ="Thala" )
    }


}



@Preview(showBackground = true)
@Composable
fun TopBarPreview ()  {
    TopBar(
        value = ""
    )
}




@Composable
fun TextComponent(textvalue:String, textSize : TextUnit, colourvalue:Color=Color.White){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
    Text(text = textvalue,
        fontSize = textSize,
        color = colourvalue,
        fontWeight = FontWeight.Bold)}


}

@Preview(showBackground = true)
@Composable
fun TextComponentPreview(){
    TextComponent(textvalue = "hello ", textSize = 24.sp,  )
}

@Composable
fun TextFieldComponent(
    onTextChanged: (name:String)-> Unit
) {

    var currentValue by remember{
        mutableStateOf("")



    }
    val localFocusManager= LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = currentValue,
        onValueChange ={
            currentValue=it
            onTextChanged(it)
        },
        placeholder = {
            Text(text = "Please let us know your name first", fontSize = 15.sp)
        },
        textStyle = TextStyle.Default.copy(fontSize = 20.sp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        }
        )


}

@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview () {
    Row(modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically){
        TextFieldComponent(
            onTextChanged = {}
        )
    }


}
@Composable
fun AnimalCard(image:Int,selected:Boolean,animalSelected: (animalName:String)-> Unit){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(24.dp)
            .size(80.dp),
        elevation = CardDefaults.cardElevation(5.dp)

    ) {
        Box (
            modifier = Modifier.fillMaxSize()
                .border(width = 1.5.dp,
                    color =if (selected)Color.Cyan else Color.Transparent,
                    shape = RoundedCornerShape(8.dp))
        ){

        Image(painter = painterResource(image) ,
            contentDescription ="Animal Image",
            modifier = Modifier.padding(10.dp)
                .clickable {
                    val animalName = when (image){
                        R.drawable.cat -> "Cat"
                        R.drawable.dog-> "Dog"
                        R.drawable.horce->"Horse"
                        R.drawable.sheep->"Sheep"
                        R.drawable.fish->"Fish"
                        R.drawable.frog->"Frog"
                        else-> "unknown"
                    }


                    animalSelected((animalName) )

                })
        }

    }
}
@Preview
@Composable
fun AnimalCardPreview(){
    AnimalCard(
        image = TODO(),
        selected = TODO(),
        animalSelected = TODO()
    )
}

@Composable
fun ButtonComponent(
    goToDetailsScreen :()-> Unit
){
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { goToDetailsScreen() }) {
        TextComponent(textvalue = "Show the Fact", textSize = 20.sp, colourvalue = Color.White)

    }
}

@Composable
fun FactComposable(value: String){
    Spacer(modifier = Modifier.size((35.dp)))
    Card(modifier = Modifier
        .padding(43.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(7.dp )
    ) {
        Column (
            modifier = Modifier.padding(24.dp,32.dp)
        ){
            Spacer(modifier =Modifier.size(29.dp))
          Text(value)
            Spacer(modifier = Modifier.size(29.dp))
        }
    }


}
@Preview
@Composable
fun FactComposablePreview(){
    FactComposable(value = "all")
}