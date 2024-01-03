package com.example.botanidrip

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("/riegos/")
    suspend fun getRiegos(): List<RiegoPrueba>

    @GET("/programados/")
    suspend fun getRegadosProgramados(): List<RegadoProgramado>

    @GET("/rm1/")
    suspend fun regarManual(): Int

    @GET("/ra1/")
    suspend fun regarAutomatico(): Int

    @GET("/ws1/")
    suspend fun obtenerEstadoAgua(): Int


    companion object {
        var apiService: ApiService? = null
        var ip: String = "http://192.168.70.158:8080"


        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(ip)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }

        fun setNewIp(ip: String) {
            this.ip = ip
        }
    }
}

