package com.example.botanidrip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.botanidrip.ui.theme.BotaniDripTheme
import kotlin.reflect.KProperty


class MainActivity : ComponentActivity() {
    val riegoViewModel by viewModels<RiegoViewModel>()
    val programadosDePrueba: List<RegadoProgramado> = listOf(
        RegadoProgramado(2, "20:00"),
        RegadoProgramado(1, "21:00")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigateHostController = rememberNavController()
            NavHost(navController = navigateHostController, startDestination = Routes.Main.titulo) {
                composable(Routes.Historial.titulo) {
                    HistoryPreview(
                        navController = navigateHostController,
                        listado = riegoViewModel.riegoListResponse
                    )
                    riegoViewModel.getRiegoList()

                }
                composable(Routes.Estadistica.titulo) {
                    ShowStatsImage(
                        navController = navigateHostController
                    )
                    riegoViewModel.obtAgua()
                }
                composable(Routes.Main.titulo) {
                    ProbarModos(
                        navController = navigateHostController,
                        inicial = true,
                        automaticos = programadosDePrueba
                    )
                }
            }
        }
    }
}


