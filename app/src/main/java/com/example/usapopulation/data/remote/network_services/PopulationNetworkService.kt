package com.example.usapopulation.data.remote.network_services

import com.example.usapopulation.data.remote.models.PopulationRespObj
import com.example.usapopulation.utils.Resource

interface PopulationNetworkService {

    suspend fun fetchPopulation(drilldowns: String, measures: String): Resource<PopulationRespObj>
}