package com.example.botanidrip

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class RiegoViewModel: ViewModel() {
    var riegoListResponse: List<RiegoPrueba> by mutableStateOf(listOf())
    var regadosProgramados: List<RegadoProgramado> by mutableStateOf(listOf())
    var regado: Int by mutableStateOf(-1)
    var hayAgua: Boolean by mutableStateOf(false)
    var errorMessage: String by mutableStateOf("")

    fun getRiegoList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                riegoListResponse = apiService.getRiegos()
            }
            catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
    fun getRegadosProgramadosList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                regadosProgramados = apiService.getRegadosProgramados()
            }
            catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
    fun obtAgua() {
        viewModelScope.launch {
            try {
                hayAgua = ApiService.getInstance().obtenerEstadoAgua() == 0
            }
            catch (e: Exception){
                errorMessage = e.message.toString()
            }


        }
    }
    fun regadoManual(): Int {
        var codigo: Int = 0
        viewModelScope.launch {
            try {
                if (ApiService.getInstance().obtenerEstadoAgua() == 1){ //SI NO HAY AGUA
                    ApiService.getInstance().regarManual()              //RIEGA
                    codigo =  0;                                        //CODIGO DE EXITO
                }
                else {
                    codigo =  1;                                        //CODIGO DE NO EXITO
                }


            }
            catch(e: Exception) {
                errorMessage = e.message.toString()
            }
        }
        return codigo; //RETORNA EL CODIGO
    }

    fun regadoAutomatico(): Int {
        var codigo: Int = 0
        viewModelScope.launch {
            try {
                if (ApiService.getInstance().obtenerEstadoAgua() == 1){
                    ApiService.getInstance().regarAutomatico()
                    codigo =  0;
                }
                else {
                    codigo =  1;
                }

            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
        return codigo
    }

}