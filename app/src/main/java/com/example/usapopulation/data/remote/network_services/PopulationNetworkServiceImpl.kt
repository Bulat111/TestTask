package com.example.usapopulation.data.remote.network_services

import android.content.Context
import com.example.usapopulation.R
import com.example.usapopulation.data.remote.api.PopulationAPI
import com.example.usapopulation.data.remote.models.PopulationRespObj
import com.example.usapopulation.utils.Resource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class PopulationNetworkServiceImpl @Inject constructor(
    private val applicationContext: Context,
    private val populationAPI: PopulationAPI
): PopulationNetworkService {

    override suspend fun fetchPopulation(drilldowns: String, measures: String): Resource<PopulationRespObj> {
        return try {
            val response: Response<PopulationRespObj> = populationAPI.getPopulation(drilldowns, measures)
            val result: PopulationRespObj? = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(applicationContext.getString(R.string.check_your_internet))
        } catch (e: Exception) {
            Resource.Error(e.message ?: applicationContext.getString(R.string.error_occurred))
        }
    }
}