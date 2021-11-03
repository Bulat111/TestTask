package com.example.usapopulation.di

import com.example.usapopulation.data.remote.network_services.PopulationNetworkService
import com.example.usapopulation.data.remote.network_services.PopulationNetworkServiceImpl
import com.example.usapopulation.repository.PopulationRepository
import com.example.usapopulation.repository.PopulationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindPopulationRepository(
        populationRepositoryImpl: PopulationRepositoryImpl
    ): PopulationRepository

    @Binds
    fun bindPopulationNetworkService(
        populationNetworkServiceImpl: PopulationNetworkServiceImpl
    ): PopulationNetworkService
}