package com.example.usapopulation.repository

import com.example.usapopulation.data.remote.models.PopulationRespObj
import com.example.usapopulation.utils.Resource

interface PopulationRepository {

    suspend fun fetchPopulation(drilldowns: String, measures: String): Resource<PopulationRespObj>
}