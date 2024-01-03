package com.example.botanidrip

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.botanidrip.ui.theme.BotaniDripTheme


class VistaGeneral : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


        }
    }
}

@Composable
fun BotonHistorial(mod: Modifier, nav: NavHostController) {
    Button(onClick = { nav.navigate(Routes.Historial.titulo) }, modifier = mod) {
        Text(text = "Mostrar Historial de Riego", fontSize = 20.sp)
    }

}

@Composable
fun BotonEstadistica(mod: Modifier, nav: NavHostController) {
    Button(onClick = { nav.navigate(Routes.Estadistica.titulo) }, modifier = mod) {
        Text(text = "Ver estadísticas del suelo", fontSize = 20.sp)
    }
}

@Composable
fun BotonRegar(mod: Modifier) {
    var dialogo = remember { mutableStateOf(false) }
    var inicial = rememberSaveable { mutableStateOf(RiegoViewModel().regado) }

    if (dialogo.value) {
        DialogoSelector(inicial.value, dialogo)
    }
    Button(
        onClick = {
            dialogo.value = true
            if (inicial.value == 1) {
                Log.e("regado", "regado")
                RiegoViewModel().regadoManual()
            }

        },
        modifier = mod
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
    ) {
        Text(text = "REGAR", fontSize = 40.sp)
    }
}


@Composable
fun DialogoExito(valor: MutableState<Boolean>) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { valor.value = !valor.value },
        confirmButton = {
            Button(onClick = { valor.value = !valor.value }) {
                Text(text = "Aceptar", fontSize = 15.sp)
            }
        },
        title = { Text(text = "Regando!", fontSize = 30.sp) },
        text = {
            Text(
                text = "La bomba dejará de regar al detectar que hay agua suficiente",
                fontSize = 20.sp
            )
        },
    )
}

@Composable
fun DialogoNoExitoso(valor: MutableState<Boolean>) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { valor.value = !valor.value },
        confirmButton = {
            Button(onClick = {
                RiegoViewModel().regadoManual()
                valor.value = !valor.value
            }) {
                Text(text = "Regar Igualmente", fontSize = 15.sp)
            }
        },
        dismissButton = {
            Button(onClick = { valor.value = !valor.value }) {
                Text(text = "Aceptar", fontSize = 15.sp)
            }
        },
        title = { Text(text = "No se ha regado!", fontSize = 30.sp) },
        text = {
            Text(
                text = "El sensor detecta que la tierra está lo suficientemente húmeda",
                fontSize = 20.sp
            )
        },
    )
}

@Composable
fun DialogoError(valor: MutableState<Boolean>) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { valor.value = !valor.value },
        confirmButton = {
            Button(onClick = { valor.value = !valor.value }) {
                Text(text = "Aceptar", fontSize = 15.sp)
            }
        },
        title = { Text(text = "Error", fontSize = 30.sp) },
        text = {
            Text(
                text = "BotaniDrip no ha iniciado completamente.\nEspere a que se realice a conexión.",
                fontSize = 20.sp
            )
        },
    )
}

@Composable
fun DialogoSelector(tipo: Int, cerrado: MutableState<Boolean>) {
    if (tipo == 1) {
        DialogoExito(valor = cerrado)
    } else if (tipo == 0) {
        DialogoNoExitoso(valor = cerrado)
    } else {
        DialogoError(valor = cerrado)
    }

}


@Composable
fun BotonAgendar(mod: Modifier) {
    Button(onClick = {}, modifier = mod) {
        Text(text = "Agendar Nuevo Regado Automático")
    }
}


@Composable
fun ModoManual(navController: NavHostController) {
    BotaniDripTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            ShowTitle(title = "Modo Manual")
            Spacer(modifier = Modifier.padding(20.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Bienvenido a BotaniDrip",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                BotonHistorial(
                    mod = Modifier.align(alignment = Alignment.Center),
                    nav = navController
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                BotonEstadistica(
                    mod = Modifier.align(alignment = Alignment.Center),
                    nav = navController
                )
            }
            Spacer(modifier = Modifier.padding(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp, 400.dp)
            ) {
                BotonRegar(mod = Modifier.align(alignment = Alignment.Center))
            }


        }
    }


}

@Composable
fun ModoAutomatico(navController: NavHostController, automaticos: List<RegadoProgramado>) {
    BotaniDripTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            ShowTitle(title = "Modo Automático")
            Spacer(modifier = Modifier.padding(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Bienvenido a BotaniDrip",
                    fontSize = 30.sp,
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                BotonHistorial(
                    mod = Modifier.align(alignment = Alignment.Center),
                    nav = navController
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                BotonEstadistica(
                    mod = Modifier.align(alignment = Alignment.Center),
                    nav = navController
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                BotonAgendar(mod = Modifier.align(alignment = Alignment.Center))
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Spacer(modifier = Modifier.padding(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                showRegadosProgramados(regados = automaticos)
            }


        }

    }
}

@Composable
fun showRegadosProgramados(regados: List<RegadoProgramado>) {
    LazyColumn {
        itemsIndexed(regados) { index, regado -> Text(text = "$index      ${regado.hora}") }
    }
}


@Composable
fun ProbarModos(
    navController: NavHostController,
    inicial: Boolean,
    automaticos: List<RegadoProgramado>
) {
    var manual by rememberSaveable { mutableStateOf(switcheable.get()) }

    if (manual) {
        Column {
            ModoManual(navController = navController)
            Button(onClick = { manual = switcheable.switch() }) {
                Text(text = "Cambiar a modo automático")
            }

        }


    } else {
        Column {
            ModoAutomatico(navController = navController, automaticos = automaticos)
            Button(onClick = { manual = switcheable.switch() }) {
                Text(text = "Cambiar a modo manual")
            }
        }
    }
}

