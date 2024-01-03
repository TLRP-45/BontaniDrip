package com.example.botanidrip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

val switcheable: Switcher = Switcher()


@Composable
fun ShowTitle(title: String){
    Box(modifier= Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.inversePrimary)){
        Text (
            text = "$title",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BackButton(){
    Box(modifier= Modifier
        .background(MaterialTheme.colorScheme.primary), ){
        Text (text = "<--", fontSize = 32.sp)
    }
}

@Composable
fun TitleWithBackButton(title: String){
    Row{
        BackButton()
        ShowTitle(title = "$title")
    }
}


@Preview
@Composable
fun CommonPreview(){
    TitleWithBackButton(title = "Prueba1")
    TitleWithBackButton(title = "Prueba2")

}

