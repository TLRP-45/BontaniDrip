package com.example.botanidrip


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.botanidrip.ui.theme.BotaniDripTheme


@Composable
fun ShowStatsImage(navController: NavHostController) {
    var inicial = rememberSaveable { mutableStateOf(RiegoViewModel().regado) }
    BotaniDripTheme {
        Row {
            Button(onClick = { navController.navigate(Routes.Main.titulo) }) {
                Text(text = "<-")
            }
            ShowTitle("Estadisticas")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                when (inicial.value) {
                    1 -> SueloMalo()
                    0 -> SueloBueno()
                    -1 -> SueloDuda()
                }

                Spacer(modifier = Modifier.padding(50.dp))


            }
        }
    }

}

@Composable
fun SueloBueno() {
    Text(text = "El estado del suelo es óptimo")
    Row() {
        Image(
            painter = painterResource(R.drawable.happy),
            contentDescription = "óptimo",
            modifier = Modifier
                .size(100.dp)

        )
        Image(
            painter = painterResource(R.drawable.bueno),
            contentDescription = "óptimo",
            modifier = Modifier
                .size(100.dp)

        )

    }
}

@Composable
fun SueloMalo() {
    Text(text = "El estado del suelo no es bueno")
    Row() {
        Image(
            painter = painterResource(R.drawable.sad),
            contentDescription = "triste",
            modifier = Modifier
                .size(100.dp)

        )
        Image(
            painter = painterResource(R.drawable.malo),
            contentDescription = "malo",
            modifier = Modifier
                .size(100.dp)

        )

    }
}

@Composable
fun SueloDuda() {
    Text(text = "No se ha establecido la conexión con BotaniDrip\nIntente más tarde")
    Row() {
        Image(
            painter = painterResource(R.drawable.time),
            contentDescription = "óptimo",
            modifier = Modifier
                .size(100.dp)

        )
        Image(
            painter = painterResource(R.drawable.question),
            contentDescription = "óptimo",
            modifier = Modifier
                .size(100.dp)

        )

    }
}



