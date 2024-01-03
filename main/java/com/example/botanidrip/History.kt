package com.example.botanidrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.botanidrip.ui.theme.BotaniDripTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun RiegoDisplay(r: RiegoPrueba) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.regadera),
            contentDescription = "regadera",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {

            Text(
                text = "Nuevo Riego Recientemente",
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium) {
                Row {
                    Text(
                        text = "ID ${r.riegoid}",
                        modifier = Modifier.padding(all = 4.dp),
                    )
                    Text(
                        text = "Fecha: ${r.fecha}",
                        modifier = Modifier.padding(all = 4.dp)
                    )
                    Text(
                        text = "Hora: ${r.hora}",
                        modifier = Modifier.padding(all = 4.dp)
                    )

                }
            }
            Column {
                if (r.automatico == 1) {
                    Text(
                        text = "Automático",
                        modifier = Modifier.padding(all = 4.dp)
                    )
                } else {
                    Text(
                        text = "Manual",
                        modifier = Modifier.padding(all = 4.dp)
                    )
                }
            }

        }


    }

}

@Composable
fun ShowRiegoHistory(riegos: List<RiegoPrueba>) {
    LazyColumn {
        itemsIndexed(riegos) { index, riego ->
            RiegoDisplay(r = riego)
        }
    }
}


@Composable
fun HistoryPreview(navController: NavHostController, listado: List<RiegoPrueba>) {
    Column {
        BotaniDripTheme {

            Row {
                Button(onClick = { navController.navigate(Routes.Main.titulo) }) {
                    Text(text = "<-")
                }
                ShowTitle("Historial de Riego")
            }
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier.padding(5.dp)) {
                    ShowRiegoHistory(riegos = listado)

                }

            }

        }
    }

}