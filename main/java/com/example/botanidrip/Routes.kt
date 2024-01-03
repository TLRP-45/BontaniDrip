package com.example.botanidrip

sealed class Routes(val titulo: String){
    object Manual: Routes(titulo ="modo_manual")
    object Automatico: Routes(titulo="modo_auto")
    object Historial: Routes(titulo="historial")
    object Estadistica: Routes(titulo="estadistica")
    object Main: Routes(titulo="main")
}
