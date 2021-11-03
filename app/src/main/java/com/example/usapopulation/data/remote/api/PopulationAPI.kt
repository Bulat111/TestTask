package com.example.usapopulation.data.remote.api

import com.example.usapopulation.data.remote.models.PopulationRespObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopulationAPI {

    @GET("/api/data")
    suspend fun getPopulation(
        @Query("drilldowns") drilldowns: String,
        @Query("measures") measures: String
    ): Response<PopulationRespObj>
}