package com.example.usapopulation.repository

import com.example.usapopulation.data.remote.models.PopulationRespObj
import com.example.usapopulation.data.remote.network_services.PopulationNetworkService
import com.example.usapopulation.utils.Resource
import javax.inject.Inject

class PopulationRepositoryImpl @Inject constructor(
    private val populationNetworkService: PopulationNetworkService
): PopulationRepository {

    override suspend fun fetchPopulation(drilldowns: String, measures: String): Resource<PopulationRespObj> =
        populationNetworkService.fetchPopulation(drilldowns, measures)
}